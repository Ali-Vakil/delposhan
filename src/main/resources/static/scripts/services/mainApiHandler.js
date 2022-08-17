app.service("mainApiHandler", function ($http){

    this.callPost = (url, data, onSuccess, onError) => {

        url = "/api/" + url;
        let request = {
            url: url,
            method: 'POST',
            data: data
        }
        $http(request).then((response) => {
            if (response != null && response.data != null) {
                let result = response.data;
                if (result.status == "SUCCESS") {
                    onSuccess(result);
                } else if (result.haseError) {
                    Swal.fire({
                        icon: 'error',
                        title: 'Oops...',
                        text: result.message,
                    })
                } else {
                    Swal.fire({
                        icon: 'error',
                        title: 'Oops...',
                        text: "unKnow Error in Data",
                    })
                }
            }
        }, (err) => {
            Swal.fire({
                icon: 'error',
                title: 'Oops...',
                text: "Error On server",
            })
        });
    }

    this.callGet = (url, onSuccess, onError) => {
        url = "/api/" + url;
        let request = {
            url: url,
            method: 'GET'
        };
        $http(request).then((response) => {
            if (response != null && response.data != null) {
                let result = response.data;
                if (result.status == "SUCCESS") {
                    onSuccess(result);
                } else if (result.haseError) {
                    Swal.fire({
                        icon: 'error',
                        title: 'Oops...',
                        text: result.message,
                    })
                } else {
                    Swal.fire({
                        icon: 'error',
                        title: 'Oops...',
                        text: "unKnow Error in Data",
                    })
                }
            }
        }, (err) => {
            if (err.status == 417) {
                $cookies.remove("userToken");
                location.href = "/login";
            }
            Swal.fire({
                icon: 'error',
                title: 'Oops...',
                text: "Error On server",
            })
            onError(err);
        });
    }

    this.callPut = (url, data, onSuccess, onError) => {
        url = "/api/" + url;
        let request = {
            url: url,
            method: 'PUT',
            data: data
        }
        $http(request).then((response) => {
            if (response != null && response.data != null) {
                let result = response.data;
                if (result.status == "SUCCESS") {
                    onSuccess(result);
                } else if (result.haseError) {
                    Swal.fire({
                        icon: 'error',
                        title: 'Oops...',
                        text: result.message,
                    })
                } else {
                    Swal.fire({
                        icon: 'error',
                        title: 'Oops...',
                        text: "unKnow Error in Data",
                    })
                }
            }
        }, (err) => {
            Swal.fire({
                icon: 'error',
                title: 'Oops...',
                text: "Error On server",
            })
            onError(err);
        });
    }

    this.callDelete = (url, onSuccess, onError) => {
        url = "/api/" + url;
        let request = {
            url: url,
            method: 'DELETE'
        }

        $http(request).then((response) => {
            if (response != null && response.data != null) {
                let result = response.data;
                if (result.status == "SUCCESS") {
                    onSuccess(result);
                } else if (result.haseError) {
                    Swal.fire({
                        icon: 'error',
                        title: 'Oops...',
                        text: result.message,
                    })
                } else {
                    Swal.fire({
                        icon: 'error',
                        title: 'Oops...',
                        text: "unKnow Error in Data",
                    })
                }
            }
        }, (err) => {
            Swal.fire({
                icon: 'error',
                title: 'Oops...',
                text: "Error On server",
            })
            onError(err);
        });
    }


})