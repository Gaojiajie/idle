//js
const app = getApp()
import { serviceUrl, get, put, post, del } from '../api/api.js'
Page({
  data: {
    canIUse: wx.canIUse('button.open-type.getUserInfo')
  },
  onLoad: function () {
    // 登录
    wx.login({
      success: res => {
        // 发送 res.code 到后台换取 openId, sessionKey, unionId
        var that = this;
        app.globalData.code = res.code;

        // 获取用户信息
        wx.getSetting({
          success: res => {
            if (res.authSetting['scope.userInfo']) {
              // 已经授权，可以直接调用 getUserInfo 获取头像昵称，不会弹框
              wx.getUserInfo({
                success: res => {

                  // 可以将 res 发送给后台解码出 unionId
                  app.globalData.userInfo = res.userInfo
                  let params = {
                    code: app.globalData.code,
                    nickName: app.globalData.userInfo.nickName,
                    avatarUrl: app.globalData.userInfo.avatarUrl,
                    gender: app.globalData.userInfo.gender
                  }
                 
                  /**跳转 */
                  wx.switchTab({
                    url: '/pages/index/index',
                  })
                  get('/weChat/getUserInfo', params).then((res) => {
                    if (res.statusCode == '200') {
                      // console.log(res.data)
                      wx.setStorage({
                        key: "uid",
                        data: res.data.id
                      })
                      // console.log("橘色" +res.data.role )
                      app.globalData.role = res.data.role
                      if (res.data.state == 1) {
                        wx.redirectTo({
                          url: '../error/error'
                        })
                      }
                      if (res.data.username == "" || res.data.username == null) {
                        wx.redirectTo({
                          url: '../register/register?id=' + res.data.id,
                        })
                      }

                      // count.getMessages(res.data.id)
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
              })
            }
          }
        })
      }
    })
    // // 查看是否授权
    // wx.getSetting({
    //   success: function (res) {
    //     if (res.authSetting['scope.userInfo']) {
    //       // 已经授权，可以直接调用 getUserInfo 获取头像昵称
    //       wx.getUserInfo({
    //         success: function (res) {
    //           console.log("121")
    //           console.log(res.userInfo)
    //         }
    //       })
    //     }
    //   }
    // })
  },
  bindGetUserInfo: function (e) {

    // 可以将 res 发送给后台解码出 unionId
    app.globalData.userInfo = e.detail.userInfo
    let params = {
      code: app.globalData.code,
      nickName: app.globalData.userInfo.nickName,
      avatarUrl: app.globalData.userInfo.avatarUrl,
      gender: app.globalData.userInfo.gender
    }
    get('/weChat/getUserInfo', params).then((res) => {
      if (res.statusCode == '200') {
        // console.log(res.data)
        wx.setStorage({
          key: "uid",
          data: res.data.id
        })
        // console.log("橘色" +res.data.role )
        app.globalData.role = res.data.role
        if (res.data.state == 1) {
          wx.redirectTo({
            url: '../error/error'
          })
        }
       if (res.data.username == "" || res.data.username == null) {
          wx.redirectTo({
            url: '../register/register?id=' + res.data.id,
          })
        } 

        // count.getMessages(res.data.id)
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
})
