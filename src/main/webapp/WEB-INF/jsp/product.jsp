<!DOCTYPE html>


<html lang="en-US">
<head>
    <title>Onlin Shop App | Product</title>
    <jsp:include page="Headers/HomeTitle.jsp"></jsp:include>
</head>

<body ng-app="onlineShopApp" ng-controller="productsCtrl">

<jsp:include page="partial/homeNav.jsp"></jsp:include>
<br/>
<div class="container-fluid">
    <div class="row">
        <div class="col col-md-2 p-3" ng-repeat="data in categoryList">
            <div class="product-cat-item">
                <a href="/product/getAll/{{data.id}}" class="product-cat-link">
                    <div class="product-cat-image">
                        <img width="100%" height="100%" ng-src="/api/utils/upload/files/{{data.image}}">
                    </div>
                    <H4 class="product-cat-title">{{data.title}}</H4>
                </a>
            </div>
        </div>
    </div>
</div>
<br/>
<div>
    <img width="100%" src="images/jeans.jpg" />
</div>
<div class="container-fluid p-3">
    <div class="row">
        <div class="col-md-3">
            <ul class="list-group">
                <a class="list-group-item" ng-click="changeSelected('Popular')" ng-class="{'active':selectedFilter == 'Popular'}">Popular</a>
                <a class="list-group-item" ng-click="changeSelected('New')" ng-class="{'active':selectedFilter == 'New'}">New</a>
                <a class="list-group-item" ng-click="changeSelected('Cheapest')" ng-class="{'active':selectedFilter == 'Cheapest'}">cheapest</a>
                <a class="list-group-item" ng-click="changeSelected('Expensive')" ng-class="{'active':selectedFilter == 'Expensive'}">Expensive</a>
            </ul>
            <br/>
        </div>

        <div class="col">
            <h1 class="text-center">{{selectedFilter}} Products</h1>
            <br/>
            <div class="container mb-3">
                <div class="row row-cols-1 row-cols-md-3 g-4">
                    <div class="col" ng-repeat="data in productsList">
                        <div class="card h-100">
                            <img ng-src="/api/utils/upload/files/{{data.image}}" class="card-img-top imageCardSize" alt="{{data.title}}">
                            <div class="card-body">
                                <h5 class="card-title">{{data.title}}</h5>
                                <p class="card-text"><b>Detail of production : <span ng-bind-html="data.description.substr(0,100)"></span></b>
                                <p class="card-text" ng-repeat="f in data.featuresDataList">
                                    {{f.key}} : {{f.value}}
                                </p>
                                <p class="card-text"><b>price :</b>{{data.price}}</p>
                            </div>
                            <div class="card-footer">
                                <small class="text-muted">{{getTime(data.lastdate)}}</small>
                                <a class="btn btn-sm btn-primary float-sm-end" href="oneProduct/{{data.id}}">Add to Basket</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>
<br/>

<jsp:include page="partial/homeFooter.jsp"></jsp:include>

</body>
</html>