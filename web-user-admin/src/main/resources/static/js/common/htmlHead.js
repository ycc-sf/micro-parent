document.writeln("		<div class=\'layui-container layui-bg-blue container\'>");
document.writeln("			<div class=\'layui-row rowdiv\'>");
document.writeln("				<div class=\'layui-col-xs6 layui-col-sm6 layui-col-md9 title\'><span onclick='loadIndex()'>基于springBoot和高德API的微需求平台</span></div>");
document.writeln("				<div class=\'layui-col-xs6 layui-col-sm6 layui-col-md3 \'>");
document.writeln("					<ul class=\'layui-nav rightnav layui-bg-blue\' lay-filter=\'\'>");
document.writeln("						<li class=\'layui-nav-item\'>");
document.writeln("					        <a href=\'javascript:;\'>");
//document.writeln("					          <img src=\'images/user.png\' class=\'layui-nav-img\'>");
document.writeln("										<span id=\'realName\'>加载中</span>");
document.writeln("					        </a>");
document.writeln("					        <dl class=\'layui-nav-child\'>");
document.writeln("					          <dd><a href=\'/html/resource/userInfo.html\'>个人资料</a></dd>");
document.writeln("					          <dd><a href=\'/html/resource/myPublish.html\'>我的发布</a></dd>");
document.writeln("					          <dd><a href=\'/html/resource/mySubscription.html\'>我的订阅</a></dd>");
document.writeln("					          <dd><a href=\'/html/resource/myComment.html\'>我的评论</a></dd>");
document.writeln("					          <dd><a href=\'/html/resource/createInfo.html\'>发布信息</a></dd>");
document.writeln("					        </dl>");
document.writeln("					    </li>");
document.writeln("					    <li class=\'layui-nav-item\'><a href=\'/login.html\'>退出</a></li>");
document.writeln("					</ul>");
document.writeln("				</div>");
document.writeln("			</div>");
document.writeln("		</div>");

function loadIndex(){
	this.location.href='/index.html';
}