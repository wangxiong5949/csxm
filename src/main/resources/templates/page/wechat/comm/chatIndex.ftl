<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, height=device-height, user-scalable=no, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0">
<meta name="format-detection" content="telephone=no">
<title>LayIM 移动版</title>
<link rel="stylesheet" href="/csxm/layuiadmin/layui/css/layui.mobile.css">
</head>
<body>

<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<script src="/csxm/layuiadmin/layui/layui.js"></script>
<script>
var userId = '${wud.userId}';
var userName = '${wud.userName}';

var layim;
layui.config({
  version: true
}).use('mobile', function(){
  var mobile = layui.mobile,
  layer = mobile.layer;
  layim = mobile.layim;
  //演示自动回复
  var autoReplay = [
    '您好，我现在有事不在，一会再和您联系。', 
    '你没发错吧？face[微笑] '
  ];
  
  layim.config({

    //获取主面板列表信息
    init: {
        ${xxStr}
    },
    
    //扩展聊天面板工具栏
    tool: [{
      alias: 'code'
      ,title: '代码'
      ,iconUnicode: '&#xe64e;'
    }],
    
    //扩展更多列表
    moreList: [{
      alias: 'find'
      ,title: '发现'
      ,iconUnicode: '&#xe628;' //图标字体的unicode，可不填
      ,iconClass: '' //图标字体的class类名
    },{
      alias: 'share'
      ,title: '分享与邀请'
      ,iconUnicode: '&#xe641;' //图标字体的unicode，可不填
      ,iconClass: '' //图标字体的class类名
    }],
    
    //,tabIndex: 1 //用户设定初始打开的Tab项下标
    //,isNewFriend: false //是否开启“新的朋友”
    isgroup: true //是否开启“群聊”
    //,chatTitleColor: '#c00' //顶部Bar颜色
    //,title: 'LayIM' //应用名，默认：我的IM
  });
  
  //创建一个会话
  /*
  layim.chat({
    id: 111111
    ,name: '许闲心'
    ,type: 'kefu' //friend、group等字符，如果是group，则创建的是群聊
    ,avatar: 'http://tp1.sinaimg.cn/1571889140/180/40030060651/1'
  });
  */
  
  //监听点击“新的朋友”
  layim.on('newFriend', function(){
    layim.panel({
      title: '新的朋友1111' //标题
      ,tpl: '<div style="padding: 10px;">自定义模版，{{d.data.test}}</div>' //模版
      ,data: { //数据
        test: '么么哒2222'
      }
    });
  });
  
  //查看聊天信息
  layim.on('detail', function(data){
    //console.log(data); //获取当前会话对象
    layim.panel({
      title: data.name + '00000000' //标题
      ,tpl: '<div style="padding: 10px;">自定义模版，<a href="http://www.layui.com/doc/modules/layim_mobile.html#ondetail" target="_blank">参考文档</a></div>' //模版
      ,data: { //数据
        test: '00000000'
      }
    });
  });
  
  //监听点击更多列表
  layim.on('moreList', function(obj){
    switch(obj.alias){
      case 'find':
        layer.msg('自定义发现动作');
        //模拟标记“发现新动态”为已读
        layim.showNew('More', false);
        layim.showNew('find', false);
      break;
      case 'share':
        layim.panel({
          title: '邀请好友3333' //标题
          ,tpl: '<div style="padding: 10px;">自定义模版，{{d.data.test}}</div>' //模版
          ,data: { //数据
            test: '么么哒4444'
          }
        });
      break;
    }
  });
  
  //监听返回
  layim.on('back', function(){
    //如果你只是弹出一个会话界面（不显示主面板），那么可通过监听返回，跳转到上一页面，如：history.back();
  });
  
  //监听自定义工具栏点击，以添加代码为例
  layim.on('tool(code)', function(insert, send){
    insert('[pre class=layui-code]123[/pre]'); //将内容插入到编辑器
    send();
  });
  
  //监听发送消息
  layim.on('sendMessage', function(data){
      var To = data.to;
      var msgChat = data.mine.content;
      $.ajax({
          url:"/csxm/wechatChat/voidChat",
          type:"post",
          data:{"msgChat":msgChat, "jsUserId":To.id, "userId":userId, "userName":userName},
          dataType:"json",
          success:function (r) {

          }
      });
  });

  //监听查看更多记录
  layim.on('chatlog', function(data, ul){
    console.log(data);
    layim.panel({
      title: '与 '+ data.name +' 的聊天记录',//标题
      tpl: '<div style="padding: 10px;">这里是模版，{{d.data.test}}</div>',//模版
      data: {//数据
        test: 'Hello'
      }
    });
  });

  //模拟"更多"有新动态
  layim.showNew('More', true);
  layim.showNew('find', true);
});

var websocket = null;
if ('WebSocket' in window) {
    // websocket = new WebSocket('ws://101.37.75.90:8090/webSocket');
    // websocket = new WebSocket('ws://localhost:8080/webSocket');
    websocket = new WebSocket('ws://192.168.0.139:8080/webSocket');
} else {
    alert("不支持");
}

//建立连接
websocket.onopen = function (event) {
    console.log("建立连接");
}

//连接关闭
websocket.onclose = function (event) {
    console.log("连接关闭");
}

//收到消息
websocket.onmessage = function (event) {
    var user = JSON.parse(event.data);
    //在另一个聊天窗口中选中的聊天对象id
    var jsUserId = user.jsUserId;
    //发送人id
    var fsUserId = user.userId;
    //发送人名称
    var fsUserName = user.userName;
    if(jsUserId == userId){
        layim.getMessage({
            username: fsUserName,
            avatar: "http://tp1.sinaimg.cn/1571889140/180/40030060651/1",
            id: fsUserId,
            type: "friend",
            cid: Math.random()*100000|0, //模拟消息id，会赋值在li的data-cid上，以便完成一些消息的操作（如撤回），可不填
            content: user.msgChat
        });
    }
}

//通讯发生错误
websocket.onerror = function (event) {
    alert("websocket通讯发生错误");
}

//关闭
window.onbeforeunload = function (event) {
    websocket.close();
}

</script>
</body>
</html>
