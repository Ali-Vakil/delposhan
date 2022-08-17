app.controller('InvoiceDetailCtrl', function ($scope, ApiHandler, $rootScope) {

    $scope.data = {};
    $scope.itemslist = [];
    $scope.InvoiceId;

    $scope.getdatalist = () => {
        let url = 'Invoice/' + $scope.InvoiceId + "/";

        ApiHandler.callGet(url, (response) => {

            $scope.data = response.datalist[0];

        }, (err) => {

        }, true);
    }

    $scope.init = () => {
        $scope.InvoiceId = $rootScope.InvoiceId;
        $scope.getdatalist();
    }



    $scope.GoToBasket = () => {

        // $scope.data.orderItems.forEach(x=>{
        //
        //     $scope.itemslist.push({
        //         id:$scope.itemslist.length,
        //         productId: x.product.id,
        //         count: x.count,
        //         price: (x.price * x.count),
        //         size: {
        //             title: x.size.title,
        //             id: x.size.id,
        //         },
        //         color: {
        //             id:   x.color.id,
        //             name: x.color.name,
        //             value:x.color.value,
        //         },
        //         product: {
        //             image: x.product.image,
        //             title: x.product.title,
        //         }
        //     })
        // })
        //$cookies.put("basket", JSON.stringify( $scope.itemslist), {path: '/'});


        ApiHandler.callPost("Payment/existInvoicePayment",$scope.data,(response)=>{
            let href = response.datalist[0].location;
            location.href = href;
        })


    }

    $scope.init();
})
;