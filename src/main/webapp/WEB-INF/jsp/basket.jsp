<!DOCTYPE html>


<html lang="en-US">
<head>
    <title>Onlin Shop App | Basket</title>
    <jsp:include page="Headers/HomeTitle.jsp"></jsp:include>
</head>

<body ng-app="onlineShopApp" ng-controller="basketCtrl">
<jsp:include page="partial/homeNav.jsp"></jsp:include>

<div class="container-fluid mt-3 mb-3">
    <div class="col" style="min-height: 500px">
        <div class="card ">
            <div class="row">
                <div class="col">
                    <div class="card">
                        <div class="card-header" style="text-align: left;font-weight: bolder">
                            Shopping Basket
                        </div>
                        <div class="card-body">


                            <table class="table table-striped table-hover">
                                <thead>
                                <tr>
                                    <th scope="col">#</th>
                                    <th scope="col">Item</th>
                                    <th scope="col">Color</th>
                                    <th scope="col">Size</th>
                                    <th scope="col">Price</th>
                                    <th scope="col">Count</th>
                                    <th scope="col">TotalItemPrice</th>
                                    <th scope="col">Actions</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr ng-show="dataList.length == 0">
                                    <td colspan="8" style="font-size:22px">Your basket is empty</td>
                                </tr>
                                <tr ng-repeat="data in dataList">
                                    <th scope="row">{{$index + 1}}</th>
                                    <td>
                                        <a title="Show Products" class="btnDeleteColor"
                                           href="/oneProduct/{{data.productId}}">
                                            <img ng-if="data.product.image"
                                                 ng-src="/api/utils/upload/files/{{data.product.image}}" width="50"/>
                                            <span>{{data.product.title}}</span>
                                        </a>
                                    </td>
                                    <td style="text-align-all: center">
                                        <label style="width:25px;height:25px;background-color:{{data.color.value}}"></label>
                                        <label>{{data.color.name}}</label>
                                    </td>
                                    <td>{{data.size.title}}</td>
                                    <td>{{data.price}}</td>
                                    <td>{{data.count}}</td>
                                    <td>{{data.price * data.count}}</td>

                                    <td>
                                        <a ng-click="DeleteItem(data)" class="btnDeleteColor"><i
                                                class="fa fa-trash-can"></i></a>
                                    </td>
                                </tr>
                                </tbody>
                                <tfoot class="footerBackground" ng-show="dataList.length > 0">
                                <tr>
                                    <td colspan="4"></td>
                                    <td>Total</td>
                                    <td>{{totoalCount}}</td>
                                    <td>{{totalPrice}}</td>
                                    <td></td>
                                </tr>
                                </tfoot>

                            </table>

                            <br>
                            <div class="text-right">
                                <a class="btn btn-outline-primary" href="/product"><i class="fa fa-basket-shopping"></i> Continue to shop</a>
                                &nbsp;
                                <a ng-show="dataList.length > 0 " class="btn btn-success" href="/payment"><i class="fa fa-credit-card"></i> purchase</a>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>
</div>
<jsp:include page="partial/homeFooter.jsp"></jsp:include>
</body>
</html>