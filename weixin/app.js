//app.js
import { get } from './pages/index/api/api.js'
import wxValidate from './pages/index/utils/WxValidate.js'
App({
  onLaunch: function () {
    var count = this
    // 展示本地存储能力
    var logs = wx.getStorageSync('logs') || []
    logs.unshift(Date.now())
    wx.setStorageSync('logs', logs)
    
   
  },
  getMessages: function(id) {
    var that = this
    setTimeout(function(){
      // console.log("1")
      get('/comments/unread/count/' + id, null).then((res) => {
        if (res.statusCode == '200') {
          wx.setTabBarBadge({
            index: 3,
            text:  res.data.toString()
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
      that.getMessages(id);
    },2000)
  },
  globalData: {
    userInfo: null,
    code: '',
    role:'',
    state:''
  },
  wxValidate: (rules, messages) => new wxValidate(rules, messages)
})