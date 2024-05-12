window.addEventListener("load", function (){
    let tbody = document.querySelector("tbody");

    tbody.onclick = function (e) {
        let tr = e.target.closest("tr");
        if (!tr)
            return;

        let id = tr.dataset.id;
        location.href = `update?id=${id}`;
    }
})