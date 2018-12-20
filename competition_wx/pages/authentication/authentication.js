Page({

  /**
   * 页面的初始数据
   */
  data: {
    username: "",
    password: ""
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {

  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function() {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function() {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function() {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function() {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function() {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function() {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function() {

  },

  login: function() {
    wx.request({
      url: 'http://localhost:8080/login',
      method: 'POST',
      data:"username="+ this.data.username +"&password=" + this.data.password,
      header: {
        //设置参数内容类型为x-www-form-urlencoded
        'content-type': 'application/x-www-form-urlencoded',
        'Accept': 'application/json'
      },
      success: function(res){
        console.log(res);
        if(res.data.state == 200){ // 如果登陆成功
            let sessionId = res.data.data;
            getApp().globalData.sessionId = sessionId;
        }
      }
    })
  },

  inputUsername: function(e) {
    this.setData({
      username: e.detail.value
    });
  },

  inputPassword: function(e) {
    this.setData({
      password: e.detail.value
    })
  }
})