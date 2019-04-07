//获取应用实例
const app = getApp()
import { get } from '../api/api.js'
// import { createQrCodeImg } from './qrcode/wxqrcode.js'
import * as echarts from '../../../ec-canvas/echarts.js';
function initChart(canvas, width, height) {
  var that = this
  const chart = echarts.init(canvas, null, {
    width: width,
    height: height
  });
  canvas.setChart(chart);

  var Goodstype = []
  get('/statistics/goods/type', null).then((res) => {
    if (res.statusCode == '200') {
      Goodstype = res.data

      var option = {
        // title: {
        //   text: '商品',                                         
        //   // subtext: '纯属虚构',
        //   x: 'center'
        // },
        tooltip: {
          trigger: 'item',
          formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        // legend: {
        //   orient: 'vertical',
        //   left: 'left',
        //   data: ['闲置数码', '家具日用', '图书音像', '鞋服配饰', '美妆洗护', '文体户外', '办公用品', '其他']                                 
        // },
        series: [
          {
            name: '',
            type: 'pie',
            radius: '30%',
            label: {
              formatter: "{b}",
              normal: {
                position: 'inner'
              }
            },
            label: {
              formatter: "{b} : {c} ({d}%)"
            },
            center: ['50%', '80%'],
            data: [
              { value: Goodstype[0], name: '数码' },
              { value: Goodstype[1], name: '日用' },
              { value: Goodstype[2], name: '图书' },
              { value: Goodstype[3], name: '鞋配' },
              { value: Goodstype[4], name: '美护' },
              { value: Goodstype[5], name: '文体' },
              { value: Goodstype[6], name: '办公' },
              { value: Goodstype[7], name: '其他' }
            ],
            itemStyle: {
              emphasis: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: 'rgba(0, 0, 0, 0.5)'
              }
            }
          }
        ]

      };
      chart.setOption(option);
      return chart;

    } else {
      wx.showToast({
        title: res.data.message,
        icon: "none",
        // image: '/pages/images/warning.png',
        duration: 2000
      })
    }
  });

}

function getBarOption(canvas, width, height) {
  var that = this
  const barChart = echarts.init(canvas, null, {
    width: width,
    height: height
  });
  canvas.setChart(barChart);


  get('/statistics/userAndGoodsCount', null).then((res) => {
    if (res.statusCode == '200') {
      var userAndGoodsCount = [];
      userAndGoodsCount = res.data
      var optionBar = {
        xAxis: {
          type: 'category',
          data: ['累计注册人数', '累计发布商品', '成交量']
        },
        yAxis: {
          type: 'value'
        },
        series: [{
          label: {
            normal: {
              show: true,
              position: 'top'
            }
          },
          data: [userAndGoodsCount[0], userAndGoodsCount[1], userAndGoodsCount[2]],
          type: 'bar'
        }]
      };
      barChart.setOption(optionBar);
      return barChart;

    } else {
      wx.showToast({
        title: res.data.message,
        icon: "none",
        // image: '/pages/images/warning.png',
        duration: 2000
      })
    }
  });
}
Page({

  /**
   * 页面的初始数据
   */
  data: {
    ecFar: {
      onInit: initChart
    },
    ecBar: {
      onInit: getBarOption
    },
    goodsCountByType: [],
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

  },
  getGoodsCountByType: function () {
    var that = this
    get('/statistics/goods/type', null).then((res) => {
      if (res.statusCode == '200') {
        that.setData({
          goodsCountByType: res.data
        })
      } else {
        wx.showToast({
          title: res.data.message,
          icon: "none",
          // image: '/pages/images/warning.png',
          duration: 2000
        })
      }
    });
  },
})