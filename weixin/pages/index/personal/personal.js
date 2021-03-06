// pages/index/personal/personal.js
var app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    uid: '',
    userInfo: '',
    role:'',
    list: [
      {
        list_tool: [
          {
            img: "../../images/personal-publish.png",
            name: "发布",
            url: "../personalGoods/personalGoods"
          },
          {
            img: "../../images/personal-like.png",
            name: "关注",
            url: "../statistics/statistics"
          }
        ]
      },
      {
        list_tool: [
          {
            img: "../../images/personal-password.png",
            name: "密码管理",
            url: "../passwordManage/passwordManage"
          },
          {
            img: "../../images/personal-info.png",
            name: "信息管理",
            url: "../userInfoManage/userInfoManage",
          }
        ]
      },
      {
        list_tool: [
          {
            img: "../../images/exit.png",
            name: "退出登录",
            url: "../login/login"
          }
        ]
      }
    ]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    // wx.showNavigationBarLoading();
    var that = this
    //调用应用实例的方法获取全局数据
    wx.getStorage({
      key: 'uid',
      success: function (res) {
        that.setData({
          uid: res.data
        })
      }
    })
    that.setData({
      userInfo: app.globalData.userInfo,
      role: app.globalData.role
    })

  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  },
  /**
   * 跳转   #F4CE73     #79CCBF
   */
  goPage: function (event) {
    var that = this;
    if (event.currentTarget.dataset.url == '../login/login') {
      console.log(event.currentTarget.dataset.url)
      wx.redirectTo({
        url: event.currentTarget.dataset.url
      })
    } else {
      wx.navigateTo({
        url: event.currentTarget.dataset.url + '?id=' + this.data.uid
      })
    }
  },
  goPageAdmin: function() {
    wx.navigateTo({
      url: '../userInfoManage/userInfoManage'
    })
  },
  goPageEchart: function () {
    wx.navigateTo({
      url: '../echart/echart'
    })
  }
})