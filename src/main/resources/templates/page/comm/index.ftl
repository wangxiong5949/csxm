<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>管理后台</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
  <link rel="stylesheet" href="/csxm/layuiadmin/layui/css/layui.css" media="all">
  <link rel="stylesheet" href="/csxm/layuiadmin/style/admin.css" media="all">
</head>
<body class="layui-layout-body">
  <div id="LAY_app">
    <div class="layui-layout layui-layout-admin">
      <div class="layui-header">
        <!-- 头部区域 -->
        <ul class="layui-nav layui-layout-left">
          <li class="layui-nav-item layadmin-flexible" lay-unselect>
            <a href="javascript:;" layadmin-event="flexible" title="侧边伸缩">
              <i class="layui-icon layui-icon-shrink-right" id="LAY_app_flexible"></i>
            </a>
          </li>
          <li class="layui-nav-item" lay-unselect>
            <a href="javascript:;" layadmin-event="refresh" title="刷新">
              <i class="layui-icon layui-icon-refresh-3"></i>
            </a>
          </li>
          <li class="layui-nav-item layui-hide-xs" lay-unselect>
            <input type="text" placeholder="搜索..." autocomplete="off" class="layui-input layui-input-search" layadmin-event="serach" lay-action="template/search.html?keywords="> 
          </li>
        </ul>
        <ul class="layui-nav layui-layout-right" lay-filter="layadmin-layout-right">
          <li class="layui-nav-item" lay-unselect>
            <a lay-href="消息中心去的地址" layadmin-event="message" lay-text="消息中心">
              <i class="layui-icon layui-icon-notice"></i>
              <!-- 如果有新消息，则显示小圆点 -->
              <span class="layui-badge-dot"></span>
            </a>
          </li>
          <li class="layui-nav-item layui-hide-xs" lay-unselect>
            <a href="javascript:;" layadmin-event="note">
              <i class="layui-icon layui-icon-note"></i>
            </a>
          </li>
          <li class="layui-nav-item layui-hide-xs" lay-unselect>
            <a href="javascript:;" layadmin-event="fullscreen">
              <i class="layui-icon layui-icon-screen-full"></i>
            </a>
          </li>
          <li class="layui-nav-item" lay-unselect>
            <a href="javascript:;">
              <cite>登陆人</cite>
            </a>
            <dl class="layui-nav-child">
              <dd><a lay-href="基本资料地址">基本资料</a></dd>
              <dd><a lay-href="修改密码地址">修改密码</a></dd>
              <hr>
              <dd layadmin-event="logout" style="text-align: center;"><a>退出</a></dd>
            </dl>
          </li>
          <li class="layui-nav-item layui-hide-xs" lay-unselect>
            <a href="javascript:;" layadmin-event="about"><i class="layui-icon layui-icon-more-vertical"></i></a>
          </li>
          <li class="layui-nav-item layui-show-xs-inline-block layui-hide-sm" lay-unselect>
            <a href="javascript:;" layadmin-event="more"><i class="layui-icon layui-icon-more-vertical"></i></a>
          </li>
        </ul>
      </div>
      <!-- 侧边菜单 -->
      <div class="layui-side layui-side-menu">
        <div class="layui-side-scroll">
          <div class="layui-logo" lay-href="home/console.html">
            <span>layuiAdmin</span>
          </div>
          <ul class="layui-nav layui-nav-tree" lay-shrink="all" id="LAY-system-side-menu" lay-filter="layadmin-system-side-menu">

          </ul>
        </div>
      </div>

      <!-- 页面标签 -->
      <div class="layadmin-pagetabs" id="LAY_app_tabs">
        <div class="layui-icon layadmin-tabs-control layui-icon-prev" layadmin-event="leftPage"></div>
        <div class="layui-icon layadmin-tabs-control layui-icon-next" layadmin-event="rightPage"></div>
        <div class="layui-icon layadmin-tabs-control layui-icon-down">
          <ul class="layui-nav layadmin-tabs-select" lay-filter="layadmin-pagetabs-nav">
            <li class="layui-nav-item" lay-unselect>
              <a href="javascript:;"></a>
              <dl class="layui-nav-child layui-anim-fadein">
                <dd layadmin-event="closeThisTabs"><a href="javascript:;">关闭当前标签页</a></dd>
                <dd layadmin-event="closeOtherTabs"><a href="javascript:;">关闭其它标签页</a></dd>
                <dd layadmin-event="closeAllTabs"><a href="javascript:;">关闭全部标签页</a></dd>
              </dl>
            </li>
          </ul>
        </div>
        <div class="layui-tab" lay-unauto lay-allowClose="true" lay-filter="layadmin-layout-tabs">
          <ul class="layui-tab-title" id="LAY_app_tabsheader">
            <li lay-id="home/console.html" lay-attr="home/console.html" class="layui-this"><i class="layui-icon layui-icon-home"></i></li>
          </ul>
        </div>
      </div>
      
      
      <!-- 主体内容 -->
      <div class="layui-body" id="LAY_app_body">
        <div class="layadmin-tabsbody-item layui-show">
          <iframe src="/comm/toCommOneText" frameborder="0" class="layadmin-iframe"></iframe>
        </div>
      </div>
      
      <!-- 辅助元素，一般用于移动设备下遮罩 -->
      <div class="layadmin-body-shade" layadmin-event="shade"></div>
    </div>
  </div>
  <script src="/csxm/comm/js/jquery-3.2.1.min.js"></script>
  <script src="/csxm/layuiadmin/layui/layui.js"></script>
  <script>
  layui.config({
    base: '/csxm/layuiadmin/' //静态资源所在路径
  }).extend({
    index: 'lib/index' //主入口模块
  }).use('index');

  layui.use(['form', 'layedit', 'laydate'], function(){
      var form = layui.form,
          layer = layui.layer,
          layedit = layui.layedit,
          laydate = layui.laydate;

  });

  //菜单显示
  showIndexMenu();
  function showIndexMenu(){
      $.ajax({
          url:"/csxm/toWeb/comm/showIndexMenu",
          type:"post",
          async: false,
          dataType:"json",
          success:function (r) {
              var code = r.code;
              var cdHtm = '<span style="color: white;">无数据</span>';
              if(code == "0"){
                  var oneList = r.data;
                  cdHtm = '';
                  for (var i = 0; i < oneList.length; i++) {
                      cdHtm += '<li data-name="component" class="layui-nav-item">';
                      cdHtm += '<a href="javascript:;" lay-tips="组件" lay-direction="2">';
                      cdHtm += ' <i class="layui-icon layui-icon-component"></i>';
                      cdHtm += '  <cite>' + oneList[i].menuName + '</cite>';
                      cdHtm += '</a>';
                      var twoList = oneList[i].twoList;
                      for (var j = 0; j < twoList.length; j++) {
                          cdHtm += '<dl class="layui-nav-child">';
                          var threeList = twoList[j].threeList;
                          if(threeList.length > 0) {
                              cdHtm += '<dd data-name="grid">';
                              cdHtm += '  <a href="javascript:;">' + twoList[j].menuName + '</a>';
                              cdHtm += '  <dl class="layui-nav-child">';
                              for (var k = 0; k < threeList.length; k++) {
                                  cdHtm += '<dd data-name="list"><a lay-href="component/grid/list.html">' + threeList[k].menuName + '</a></dd>';
                              }
                              cdHtm += '  </dl>';
                              cdHtm += '</dd>';
                          } else {
                              cdHtm += '<dd data-name="button">';
                              cdHtm += '   <a lay-href="component/button/index.html">' + twoList[j].menuName + '</a>';
                              cdHtm += '</dd>';
                          }
                          cdHtm += '</dl>';
                      }
                      cdHtm += '</li>';
                  }
              }
              $("#LAY-system-side-menu").html(cdHtm);
          }
      });
  }
  </script>
</body>
</html>