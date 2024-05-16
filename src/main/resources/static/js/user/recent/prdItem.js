export default {
    data(){
        return {
            checkVisible : false,
            btnStatus : 'delete'
        }
    },
   template : `
        <section class="d:flex gap:4">
            <h1 class="d:none">상품 아이템</h1>
            <div class="d:flex ai:center">
                <span class="icon icon:x_circle_fill icon-color:base-6 icon-size:4 csr:pointer"
                      :class="[checkVisible ? '' : 'd:none']">아이콘</span>
            </div>
            <div class="d:flex gap:4">
                <div>
                    <img class="w:3"
                         src="/img/carrot.png" th:src="@{/img/baechoo.png}"/>
                </div>
                <div class="d:flex fl-dir:column jc:center gap:4">
                    <div class="d:flex fl-dir:column gap:4">
                        <div>텃밭 흙 배추, 2개입</div>
                        <div><b>6500원</b></div>
                    </div>
                    <div>
                        <span class="icon icon:thumbs_up icon-color:base-6 icon-size:2 csr:pointer">최고예요 아이콘</span>
                        <span>100</span>
                    </div>
                </div>
            </div>
        </section>
    `
}