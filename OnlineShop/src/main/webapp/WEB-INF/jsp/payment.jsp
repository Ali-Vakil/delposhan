<!DOCTYPE html>


<html lang="en-US">
<head>
    <title>Onlin Shop App | Payment</title>
    <jsp:include page="Headers/HomeTitle.jsp"></jsp:include>
</head>

<body ng-app="onlineShopApp" ng-controller="paymentCtrl">
<jsp:include page="partial/homeNav.jsp"></jsp:include>


<div class="container-fluid mt-3 mb-3">

    <div class="col" style="min-height: 500px">
        <div class="row">
            <div class="col">
                <div class="card">
                    <div class="card-header" style="text-align: left;font-weight: bolder">
                        <h5>Payment Information</h5>
                    </div>
                    <div class="card-body">

                        <table class="table table-striped table-hover">
                            <thead>
                            <tr>
                                <th scope="col">#</th>
                                <th scope="col">Item</th>
                                <%--                                <th scope="col">Color</th>--%>
                                <%--                                <th scope="col">Size</th>--%>
                                <%--                                <th scope="col">Price</th>--%>
                                <th scope="col">Count</th>
                                <th scope="col">TotalItemPrice</th>
                            </tr>
                            </thead>
                            <tbody>
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
                                <td>{{data.count}}</td>
                                <td>{{data.price * data.count}}</td>

                            </tr>
                            </tbody>
                            <tfoot class="footerBackground" ng-show="dataList.length > 0">
                            <tr>
                                <td></td>
                                <td>Total</td>
                                <td>{{totoalCount}}</td>
                                <td>{{totalPrice}}</td>
                                <td></td>
                            </tr>
                            </tfoot>

                        </table>
                        <br>
                        <div>
                            <label for="PaymentType">PaymentType</label>
                            <div id="PaymentType" class="form-group mb-2">
                                <select ng-model="PymentType">
                                    <option value="zarinpal"> zarinpal</option>
                                </select>
                            </div>
                        </div>
                        <br>

                        <div class="text-right">
                            <a class="btn btn-success" ng-click="gotoPayment()"><i class="fa fa-credit-card"></i>
                                purchase</a>
                        </div>
                    </div>
                </div>

            </div>
            <div class="col">
                <div class="card">
                    <div class="card-header" style="text-align: left;font-weight: bolder">
                        <h5>User Information</h5>
                    </div>
                    <div class="card-body">

                        <div class="form-group mb-2">
                            <input type="text" placeholder="FirstName" class="form-control" id="firstName"
                                  onchange="clearBorder('firstName')"  ng-model="data.firstName">
                        </div>
                        <div class="form-group mb-2">
                            <input type="text" placeholder="LastName" class="form-control" id="lastName"
                                   onchange="clearBorder('lastName')" ng-model="data.lastName">
                        </div>
                        <div class="form-group mb-2">
                            <input type="text" placeholder="Username" class="form-control" id="username"
                                   onchange="clearBorder('username')" ng-model="data.username">
                        </div>
                        <div class="form-group mb-2">
                            <input ng-hide="userLogin" type="password" placeholder="password" class="form-control" id="password"
                                   onchange="clearBorder('password')"  ng-model="data.password">
                        </div>
                        <div class="form-group mb-2">
                            <input type="email" placeholder="Email" class="form-control" id="Email"
                                   onchange="clearBorder('Email')" ng-model="data.email">
                        </div>
                        <div class="form-group mb-2">
                            <input type="text" placeholder="Mobile" class="form-control" id="mobile"
                                   onchange="clearBorder('mobile')"  ng-model="data.mobile">
                        </div>
                        <div class="form-group mb-2">
                            <input type="tel" placeholder="Tel" class="form-control" id="tel" ng-model="data.tel">
                        </div>
                        <div class="form-group mb-2">
                            <input type="text" placeholder="Address" class="form-control" id="address"
                                   onchange="clearBorder('address')"  ng-model="data.address">
                        </div>
                        <div class="form-group mb-2">
                            <input type="text" placeholder="PostalCode" class="form-control" id="postalCod"
                                   onchange="clearBorder('postalCod')" ng-model="data.postalCode">
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
<script>
    function clearBorder(id){
        document.getElementById(id).style.border = null;
    }
</script>