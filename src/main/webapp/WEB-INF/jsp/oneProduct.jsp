<!DOCTYPE html>


<html lang="en-US">
<head>
    <title>Onlin Shop App | Product</title>
    <jsp:include page="Headers/HomeTitle.jsp"></jsp:include>

    <%
        Long dataId = (Long) request.getAttribute("dataId");
    %>
</head>

<body ng-app="onlineShopApp" ng-controller="oneProductCtrl">
<jsp:include page="partial/homeNav.jsp"></jsp:include>
<div class="container-fluid m-1" ng-init="init(<%=dataId%>)">
    <div class="row">
        <div class="col col-md-4">
            <img ng-if="data.image" ng-src="/api/utils/upload/files/{{data.image}}" width="100%"/>

            <div class="row" style="text-align: center;border: #1a1e21 solid 1px">
                <label >{{data.price}}</label>
            </div>
        </div>
        <div class="col">
            <div class="card">
                <div class="card-header">
                    {{data.title}}
                </div>
                <div class="card-body" ng-controller="contentCtrl">
                    <h5 class="card-title">On line Shop</h5>
                    <P ng-bind-html="data.description"></P>
                    <hr>
                    <label>Featurse :</label>
                    <ul id="featuer">
                        <li ng-repeat="f in featuresList">{{f.key}} : {{f.value}}</li>
                    </ul>
                    <hr>
                    <div class="row">
                        <div class="container-fluid">
                            <h3>Colors</h3>
                            <span ng-repeat="data in productcolorlist">
                                    <input type="radio" class="btn-check" name="options-outlined" id="color{{data.id}}"
                                           ng-click="whatColor()" autocomplete="off">
                                    <label class="btn" for="color{{data.id}}" id="color_lbl_{{data.id}}"
                                           style="width:25px;height:25px;background-color:{{data.value}};color:#bfd1ec;"></label>
                                </span>
                        </div>
                    </div>
                    <hr>
                    <div class="row">
                        <div class="container-fluid">
                            <h3>Size</h3>
                            <span ng-repeat="data in productsizelist">
                                    <input type="radio" class="btn-check" name="options-outlined" id="size{{data.id}}"
                                           ng-click="whatSize()" autocomplete="off">
                                    <label class="btn" for="size{{data.id}}"
                                           id="size_lbl{{data.id}}">{{data.title}}</label>
                            </span>
                        </div>
                    </div>
                    <hr>
                    <div class="row m-1">
                        <span>
                            <label for="count">count :</label>
                            <input type="number" id="count" style="width:50px" value="1">
                        </span>
                    </div>
                    <a href="#" class="btn btn-primary" ng-click="addToBasket()"> + Add to Basket</a>
                    &nbsp;
                    <a href="/product" class="btn btn-outline-primary">Continue To Shop</a>
                </div>

                <div class="card-footer text-muted">
                    <i class="fa fa-eye"></i>
                    <span>{{data.visitCount}}</span>
                    &nbsp;
                    <i class="fa fa-calendar-alt"></i>
                    <span ng-if="data.lastdate">{{getTime(data.lastdate)}}</span>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="partial/homeFooter.jsp"></jsp:include>
</body>
</html>