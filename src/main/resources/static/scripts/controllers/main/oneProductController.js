app.controller("oneProductCtrl", function ($scope, mainApiHandler, $rootScope,$cookies) {

    $rootScope.page_ = "Product";

    $scope.dataId = 0
    $scope.data = {};
    $scope.price = 0;
    $scope.featuresList = [];
    $scope.orderCount = 1




    $scope.getProductData = () => {
        let url = 'product/info/' + $scope.dataId;

        mainApiHandler.callGet(url, (response) => {
            $scope.data = response.datalist[0];
            $scope.price = $scope.data.price;
            $scope.featuresList =$scope.data.featuresDataList;
            $scope.getcolorsList();
            $scope.getSizesList();
        });
    }


    $scope.init = (id) => {
        $scope.dataId = id;
        $scope.getProductData();

    }

    $scope.getTime = (date) => {
        let Time = date.substring(0, 10);
        return Time;
    }

    $scope.orderItem = {};
    $scope.orderItems = [];
    $scope.addToBasket = () => {

        if($scope.orderCount <= 0){
            Swal.fire({
                title: "Warning",
                text: "Cannot Add 0 number Of product",
                icon: 'warning',
                showCancelButton: false,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Ok!'
            })
            return;
        }
        if($scope.selectColor == null || $scope.selectColor ==  undefined){
            Swal.fire({
                title: "Warning",
                text: "Please Choose Color",
                icon: 'warning',
                showCancelButton: false,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Ok!'
            })
            return;
        }
        if($scope.selectSize == null || $scope.selectSize == undefined){
            Swal.fire({
                title: "Warning",
                text: "Please Choose Size",
                icon: 'warning',
                showCancelButton: false,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Ok!'
            })
            return;
        }

        let count = document.getElementById("count")

        $scope.orderCount = count.value;


        $scope.orderItem.productId = $scope.dataId;
        $scope.orderItem.count = $scope.orderCount;
        $scope.orderItem.price = $scope.price;
        $scope.orderItem.size = $scope.selectSize;
        $scope.orderItem.color = $scope.selectColor;


        let exists = false;
        for (let i = 0; i < $scope.orderItems.length; i++) {

            let s_size = $scope.orderItems[i].size;
            let s_color = $scope.orderItems[i].color;
            if ($scope.orderItems[i].productId == $scope.orderItem.productId
                && s_size.id == $scope.orderItem.size.id
                && s_color.id == $scope.orderItem.color.id) {
                exists = true;
                $scope.orderItems[i].count ++;
                Swal.fire({
                    title: $scope.data.title,
                    text: "Add To Basket ("+$scope.orderItems[i].count +")",
                    icon: 'success',
                    showCancelButton: false,
                    confirmButtonColor: '#3085d6',
                    cancelButtonColor: '#d33',
                    confirmButtonText: 'Ok!'
                })
                break;
            }
        }
        if (!exists) {
            $scope.orderItems.push({
                id: $scope.orderItems.length +1,
                productId : $scope.dataId,
                count : $scope.orderCount,
                price : $scope.price,
                size : {
                   title : $scope.selectSize.title,
                    id: $scope.selectSize.id,
                },
                color:{
                    id:$scope.selectColor.id,
                    name : $scope.selectColor.name,
                    value : $scope.selectColor.value,
                },
                product: {
                    image:$scope.data.image,
                    title:$scope.data.title,
                }
            });
            Swal.fire({
                title: $scope.data.title,
                text: "Add To Basket",
                icon: 'success',
                showCancelButton: false,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Ok!'
            })


        }
        $cookies.put("basket", JSON.stringify($scope.orderItems),{path: '/'});
    }

    $scope.color = {};
    $scope.productcolorlist = [];
    $scope.selectColor = null;
    $scope.getcolorsList = () => {
        let url = 'color/';

        mainApiHandler.callGet(url, (response) => {
            let colorTemp = []
            colorTemp = response.datalist;
            for (color_ in $scope.data.colors) {
                let id = $scope.data.colors[color_];
                for (let i = 0; i < colorTemp.length; i++) {
                    if(colorTemp[i].id == id){
                        $scope.productcolorlist.push(colorTemp[i]);
                        break;
                    }
                }
            }
        });
    }
    $scope.whatColor = () => {
        for (let i = 0; i < $scope.productcolorlist.length; i++) {
            let color_id = "color" + $scope.productcolorlist[i].id;
            let label_id = "color_lbl_" + $scope.productcolorlist[i].id;
            if (document.getElementById(color_id).checked) {
                let lbl = document.getElementById(label_id);
                lbl.style.opacity = "100%";
                $scope.selectColor = $scope.productcolorlist[i]
            } else {
                let lbl = document.getElementById(label_id);
                lbl.style.opacity = "50%"
            }

        }


    }

    $scope.size_ = {};
    $scope.productsizelist = [];
    $scope.selectSize = null;
    $scope.getSizesList = () => {
        let url = 'size/';

        mainApiHandler.callGet(url, (response) => {
            let sizeTemp = [];
            sizeTemp = response.datalist;
            for (size_ in $scope.data.sizes) {
                let id = $scope.data.sizes[size_];
                for (let i = 0; i < sizeTemp.length; i++) {
                    if(sizeTemp[i].id == id){
                    $scope.productsizelist.push(sizeTemp[i]);
                    break;
                    }
                }
            }
        });
    }
    $scope.whatSize = () => {
        for (let i = 0; i < $scope.productsizelist.length; i++) {
            let size_id = "size" + $scope.productsizelist[i].id;
            let label_id = "size_lbl" + $scope.productsizelist[i].id;
            if (document.getElementById(size_id).checked) {
                let lbl = document.getElementById(label_id);
                lbl.style.opacity = "100%";
                $scope.selectSize = $scope.productsizelist[i]
            } else {
                let lbl = document.getElementById(label_id);
                lbl.style.opacity = "50%"
            }

        }


    }


    $scope.loadOrderItems = ()=>{
        if($cookies.get("basket") == undefined ||
            $cookies.get("basket") == null){
            $scope.orderItems = [];
            return;
        }
        $scope.orderItems = JSON.parse($cookies.get("basket"));

    }
    $scope.loadOrderItems();
});