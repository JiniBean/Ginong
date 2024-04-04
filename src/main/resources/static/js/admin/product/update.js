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

// const button = document.querySelector('.calendar-icon');
// button.addEventListener('click', function()
// {
//     const input = document.querySelector('input[name="foo2"]');
//     const datepicker = new Datepicker(input, {});
//     datepicker.show();
// });

// const elem = document.getElementById('foo');
// const rangepicker = new DateRangePicker(elem, {
//     // ...options
// });

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

