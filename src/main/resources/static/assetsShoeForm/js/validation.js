window.onload = function () {
        const shoeForm = document.querySelector("#shoe-form");
        let loadingScreen = document.querySelector(".loading-screen");
        let brandInput = document.querySelector("#brand");
        let modelInput = document.querySelector("#model");
        let errorLabel  = document.querySelector("#error-info");
        const submitButton = document.querySelector(".btn");
        submitButton.addEventListener("click" , function () {
            if (shoeForm.brand.value =="" && shoeForm.model.value == "" ){
               brandInput.classList.add("is-invalid");
               modelInput.classList.add("is-invalid");
               errorLabel.classList.remove("not-active")
            }else if (shoeForm.model.value == ""){
                modelInput.classList.add("is-invalid");
                errorLabel.classList.remove("not-active")
            }else if (shoeForm.brand.value ==""){
                brandInput.classList.add("is-invalid");
                errorLabel.classList.remove("not-active")
            }else{
                brandInput.classList.remove("is-invalid");
                modelInput.classList.remove("is-invalid");
                errorLabel.classList.add("not-active");
                loadingScreen.classList.remove("not-active");
                shoeForm.submit();
            }

        },false);
};