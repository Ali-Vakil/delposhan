<!DOCTYPE html>


<html lang="en-US">
<head>
    <title>Onlin Shop App | Blog</title>
    <jsp:include page="Headers/HomeTitle.jsp"></jsp:include>
</head>

<body ng-app="onlineShopApp" ng-controller="blogCtrl">
<div>
    <jsp:include page="partial/homeNav.jsp"></jsp:include>

    <div>
        <img src="images/blog.jpg" width="100%">
    </div>
    <br/>
    <div class="container-fluid">
        <div class="row p-2">
            <div class="card mb-2" ng-repeat="item in blogList">
                <div class="row">
                    <div class="col col-md-2">
                        <img ng-if="item.image" ng-src="/api/utils/upload/files/{{item.image}}"
                             class="card-img" width="100%" height="100%" alt="{{item.title}}">
                    </div>
                    <div class="col col-md-10" style="max-height:200px">
                        <div class="card-body" >
                            <div style="height:20px;margin-bottom:4px"><h5 class="card-title">{{item.title | removeHTMLTags}}</h5></div>
                            <div style="height:100px" class="card-text" ng-bind-html="item.description.substr(0,300)"></div>
                            <div  style="height:20px" class="card-text"><small class="text-muted">{{getTime(item.publishDate)}} </small>
                                <a href="blog/info/{{item.id}}" class="btn btn-primary btn-sm float-end m-1">Read more...</a>
                            </div>
                            <br/>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div>
                <br/>
                <nav>
                    <ul class="pagination pagination-sm">
                        <li ng-repeat="i in range(pageCount) track by $index" class="page-item" aria-current="page">
                            <span class="page-link" ng-click="changPage($index)">
                                {{$index + 1 }}
                            </span>
                        </li>
                    </ul>
                </nav>
            </div>
        </div>
    </div>
    <jsp:include page="partial/homeFooter.jsp"></jsp:include>
</div>
</body>
</html>