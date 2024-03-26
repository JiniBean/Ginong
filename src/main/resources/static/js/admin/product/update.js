window.addEventListener("load", function () {
    const dropdownButtonCategory = document.getElementById("dropdown-btn-category");
    const dropdownListCategory = document.getElementById("dropdown-list-category");

    dropdownButtonCategory.addEventListener("click", function () {
        dropdownListCategory.classList.toggle("active");
    });
});
window.addEventListener("load", function () {
    const dropdownButtonKeepcase = document.getElementById("dropdown-btn-keepcase");
    const dropdownListKeepcase = document.getElementById("dropdown-list-keepcase");

    dropdownButtonKeepcase.addEventListener("click", function () {
        dropdownListKeepcase.classList.toggle("active");
    });
});

const button = document.querySelector('.calendar-icon');
button.addEventListener('click', function()
{
    const input = document.querySelector('input[name="foo2"]');
    const datepicker = new Datepicker(input, {});
    datepicker.show();
});

const elem = document.getElementById('foo');
const rangepicker = new DateRangePicker(elem, {
    // ...options
});

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