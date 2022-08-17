<!DOCTYPE html>


<html lang="en-US">
<head>
    <title>Online Shop App | Blog</title>
    <jsp:include page="Headers/HomeTitle.jsp"></jsp:include>

    <%
        Long dataId = (Long) request.getAttribute("dataId");
    %>
</head>
<body ng-app="onlineShopApp" ng-controller="blogInfoCtrl">

<jsp:include page="partial/homeNav.jsp"></jsp:include>

<div class="container-fluid mt-3 mb-3" ng-init="init(<%=dataId%>)">
    <div class="col">
        <div class="card text-center">
            <div class="card-header">
                {{data.title}}
                <a class="btn btn-sm btn-primary float-end" href="/blog">
                    <i class="fa fa-square-caret-left"></i>
                    Return </a>
            </div>
            <div class="card-body">
                <div>
                    <img ng-if="data.image" ng-src="/api/utils/upload/files/{{data.image}}" width="70%" height="300" />
                </div>
                <br/>
                <hr/>
                <br/>
                <h4 class="card-title">{{data.title}}</h4>
                <hr/>
                <h6>{{data.subtitle}}</h6>
                <P ng-bind-html="data.description"></P>

            </div>
            <div class="card-footer text-muted">
                <i class="fa fa-calendar"></i>
                <span>{{getTime(data.publishDate)}}</span>
                &nbsp;&nbsp;
                <i class="fa fa-eye"></i>
                <span>{{data.visitCount}}</span>

            </div>
        </div>
    </div>
</div>


<jsp:include page="partial/homeFooter.jsp"></jsp:include>
</body>
</html>