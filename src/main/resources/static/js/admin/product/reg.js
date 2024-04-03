// 생성자 및 add 함수 추가

function InputFileList(input){
    this.input = input;
}

InputFileList.prototype = {
    add:function (file){
        var dt = new DataTransfer();
        var files = this.input.files;

        for(var existingFile of files)
            dt.items.add(existingFile);

        //추가로 담는 파일
        dt.items.add(file);
        this.input.files = dt.files;
    }
};


// 카테고리 버튼 css
document.addEventListener("DOMContentLoaded", function() {
    var selectedOption = document.getElementById('selectedOptionCategory');
    var options = document.querySelectorAll('.option');
    var selectedCategoryInput = document.getElementById('selectedCategory');

    selectedOption.addEventListener('click', function() {
        var optionList = this.nextElementSibling;
        optionList.style.display = (optionList.style.display === 'block') ? 'none' : 'block';
    });

    options.forEach(function(option) {
        option.addEventListener('click', function() {
            var selectedValueCategory = this.getAttribute('data-value');
            selectedOption.textContent = this.textContent;
            selectedCategoryInput.value = selectedValueCategory; // hidden input에 선택된 값을 설정
            this.parentNode.style.display = 'none';
        });
    });

    // 드롭다운 닫기
    document.addEventListener('click', function(event) {
        if (!selectedOption.contains(event.target)) {
            options.forEach(function(option) {
                option.parentNode.style.display = 'none';
            });
        }
    });
});
// weightCategory 선택 버튼
document.addEventListener("DOMContentLoaded", function() {
    var selectedOptionWeight = document.getElementById('selectedOptionWeight');
    var optionsWeight = document.querySelectorAll('.optionWeight');
    var selectedWeightInput = document.getElementById('selectedWeight');

    selectedOptionWeight.addEventListener('click', function() {
        var optionWeightList = this.nextElementSibling;
        optionWeightList.style.display = (optionWeightList.style.display === 'block') ? 'none' : 'block';
    });

    optionsWeight.forEach(function(option) {
        option.addEventListener('click', function() {
            var selectedValueWeight = this.getAttribute('data-value');
            selectedOptionWeight.textContent = this.textContent;
            selectedWeightInput.value = selectedValueWeight; // hidden input에 선택된 값을 설정
            this.parentNode.style.display = 'none';
        });
    });

    // 드롭다운 닫기
    document.addEventListener('click', function(event) {
        if (!selectedOptionWeight.contains(event.target)) {
            optionsWeight.forEach(function(option) {
                option.parentNode.style.display = 'none';
            });
        }
    });
});

//quantityCategory 선택 버튼

document.addEventListener("DOMContentLoaded", function() {
    var selectedOptionQuantity = document.getElementById('selectedOptionQuantity');
    var optionsQuantity = document.querySelectorAll('.optionQuantity');
    var selectedQuantityInput = document.getElementById('selectedQuantity');

    selectedOptionQuantity.addEventListener('click', function() {
        var optionQuantityList = this.nextElementSibling;
        optionQuantityList.style.display = (optionQuantityList.style.display === 'block') ? 'none' : 'block';
    });

    optionsQuantity.forEach(function(option) {
        option.addEventListener('click', function() {
            var selectedValueQuantity = this.getAttribute('data-value');
            selectedOptionQuantity.textContent = this.textContent;
            selectedQuantityInput.value = selectedValueQuantity; // hidden input에 선택된 값을 설정
            this.parentNode.style.display = 'none';
        });
    });

    // 드롭다운 닫기
    document.addEventListener('click', function(event) {
        if (!selectedOptionQuantity.contains(event.target)) {
            optionsQuantity.forEach(function(option) {
                option.parentNode.style.display = 'none';
            });
        }
    });
});

//상품 보관유형 선택 버튼

document.addEventListener("DOMContentLoaded", function() {
    var selectedOptionStorage = document.getElementById('selectedOptionStorage');
    var optionsStorage = document.querySelectorAll('.optionStorage');
    var selectedStorageInput = document.getElementById('selectedStorage');

    selectedOptionStorage.addEventListener('click', function() {
        var optionStorageList = this.nextElementSibling;
        optionStorageList.style.display = (optionStorageList.style.display === 'block') ? 'none' : 'block';
    });

    optionsStorage.forEach(function(option) {
        option.addEventListener('click', function() {
            var selectedValueStorage = this.getAttribute('data-value');
            selectedOptionStorage.textContent = this.textContent;
            selectedStorageInput.value = selectedValueStorage; // hidden input에 선택된 값을 설정
            this.parentNode.style.display = 'none';
        });
    });

    // 드롭다운 닫기
    document.addEventListener('click', function(event) {
        if (!selectedOptionStorage.contains(event.target)) {
            optionsStorage.forEach(function(option) {
                option.parentNode.style.display = 'none';
            });
        }
    });
});

// =============================================================================
// vanillajs - datepicker
// const button = document.querySelector('.calendar-icon');
// button.addEventListener('click', function()
// {
//     const input = document.querySelector('input[name="madeDate"]');
//     const datepicker = new Datepicker(input, {});
//     datepicker.show();

//     input.addEventListener('change', function() {

//     });
// });

// ===============================================================
// 사진 넣기 기능
window.addEventListener("load", function () {
    var regForm = this.document.querySelector("#reg-form");
    var imgInput = regForm.querySelector(".img-input");
    var preview = regForm.querySelector(".preview");
    //var previewImage = previewPanel.getElementsByTagName("img")[0];
    var upload = regForm.querySelector(".upload");

    // 드래그
    preview.ondragenter = function (e){
        preview.classList.add("bd");
        preview.classList.add("bd-color:main-6");
    };

    preview.ondragleave = function (e){
        preview.classList.remove("bd");
        preview.classList.remove("bd-color:main-6");
        preview.classList.remove("invalid");
    };

    preview.ondragover = function (e){
        e.stopPropagation();
        e.preventDefault();

        var valid = e.dataTransfer &&
            e.dataTransfer.types &&
            e.dataTransfer.types.indexOf("Files") >= 0;

        if (!valid)
            preview.classList.add("invalid");
        else
            preview.classList.remove("invalid");
    };

    preview.ondrop = function (e){
        e.stopPropagation();
        e.preventDefault();
        preview.classList.remove("bd");
        preview.classList.remove("bd-color:main-6");

        var files = e.dataTransfer.files;
        var file = files[0];

        new InputFileList(imgInput).add(file);

        if (file.type.indexOf("image/")!==0){
            alert("이미지만 업로드 할 수 있습니다.")
            return;
        } // 타입 제약

        if (file.size > 100 * 1024){
            alert("크기는 100KB 이하만 얿로드 할 수 있습니다.")
            return;
        }// 크기 제약

        var reader = new FileReader();
        reader.onload = function (e){
            var img = document.createElement("img");
            img.src = e.target.result;
            // previewPanner = appendChild();
            preview.append(img);

            setTimeout(() => {
                img.classList.add("slide-in");
            },10);
        };
        reader.readAsDataURL(file);

        console.log(e.dataTransfer.types);

    };







    imgInput.oninput = function(e) {
       var files = imgInput.files;

       for (file of files) {
            if (file.type.indexOf("image/") != 0) {// file type 제약
                alert("이미지 파일만 업로드 할 수 있습니다.");
                return ;
            }

            if (file.size > 3000*1024) {// file size 제약
                alert("3MB보다 크기가 작은 파일만 업로드 할 수 있습니다.");
                return ;
            }

            // 입력상자에 있는 파일을 읽어들이는 것
            var reader = new FileReader();
            reader.onload = function(e) {
            
                var img = document.createElement("img");
                img.src = e.target.result;

                console.log(e.target.result);
                preview.append(img);          // element가 가진 기능

            }
            reader.readAsDataURL(file);

            console.log("file : ", file.name);
        }

        console.log("Hello!");
        //console.log(previewImages);
    };


























    function removeImage(index) {
        // // 이미지 엘리먼트를 미리보기 패널에서 제거
        // previewPanel.removeChild(images[index]);

        // // 배열에서 이미지 엘리먼트 제거
        // images.splice(index, 1);

    }

        
    // previewImages.onclick = function(e) {
    //     if (!e.target.tagName("img"))
    //         console.log("헤헤");
    // }
});











/*
function getImageFiles(e) {
    const uploadFiles = [];
    const files = e.currentTarget.files;
    const imagePreview = document.querySelector('.image-preview');
    const docFrag = new DocumentFragment();

    const uploadedImagesCount = imagePreview.querySelectorAll('li').length;

    if (uploadedImagesCount + files.length > 4) {
        alert('이미지는 최대 4개 까지 업로드가 가능합니다.');
        return;
    }


    // 파일 타입 검사
    [...files].forEach(file => {
        if (!file.type.match("image/.*")) {
            alert('이미지 파일만 업로드가 가능합니다.');
            return
        }

        // 파일 갯수 검사
        if ([...files].length < 4) {
            uploadFiles.push(file);
            const reader = new FileReader();
            reader.onload = (e) => {
                const preview = createElement(e, file);
                imagePreview.appendChild(preview);
            };
            reader.readAsDataURL(file);
        }
    });
}

function createElement(e, file) {
    const li = document.createElement('li');
    const img = document.createElement('img');
    img.setAttribute('src', e.target.result);
    img.setAttribute('data-file', file.name);
    li.appendChild(img);

    const removeBtn = document.createElement('span');
    removeBtn.innerText = 'x';
    removeBtn.classList.add('remove-btn');
    removeBtn.addEventListener('click', () => removeImage(li));
    li.appendChild(removeBtn);

    return li;
}

function removeImage(li) {
    li.parentNode.removeChild(li);
}

const realUpload = document.querySelector('.real-upload');
const upload = document.querySelector('.upload');

upload.addEventListener('click', () => realUpload.click());

realUpload.addEventListener('change', getImageFiles);

*/
