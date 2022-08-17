<!DOCTYPE html>


<html lang="en-US">
<head>
    <title>Online Shop App | ProductCategory</title>
    <jsp:include page="Headers/HomeTitle.jsp"></jsp:include>

    <%
        Long dataId = (Long) request.getAttribute("dataId");
    %>
</head>
<body ng-app="onlineShopApp" ng-controller="productCategoryCtrl">

<jsp:include page="partial/homeNav.jsp"></jsp:include>

<div class="container-fluid mt-3 mb-3" ng-init="init(<%=dataId%>)">
    <div class="row">
        <div class="col">
            <div class="col">
                <div ng-controller="productCtrl" class="m-1">
                    <h1 class="text-center">{{categoryTitle}}</h1>
                    <br/>
                    <div class="container mb-3">
                        <div class="row row-cols-1 row-cols-md-3 g-4">
                            <div class="col" ng-repeat="data in dataList">
                                <div class="card h-100">
                                    <img ng-src="/api/utils/upload/files/{{data.image}}" class="card-img-top" alt="{{data.title}}">
                                    <div class="card-body">
                                        <h5 class="card-title">{{data.title}}</h5>
                                        <p class="card-text"><b>Detail of production : <span ng-bind-html="data.description"></span></b>
                                        <p class="card-text" ng-repeat="f in data.featuresDataList">
                                            {{f.key}} : {{f.value}}
                                        </p>
                                        <p class="card-text"><b>price :</b>{{data.price}}</p>
                                    </div>
                                    <div class="card-footer">
                                        <small class="text-muted">{{getTime(data.addDate)}}</small>
                                        <a class="btn btn-sm btn-primary float-sm-end" href="/oneProduct/{{data.id}}">Add to Basket</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <br/>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col"></div>
        <div class="col-md-3">
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
        <div class="col"></div>
    </div>
</div>


<jsp:include page="partial/homeFooter.jsp"></jsp:include>
</body>
</html>