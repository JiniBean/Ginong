
const { createApp } = Vue;

createApp({
    data() {
        return {
            info: [],
            ids: [],
            images: [],
            names: [],
            state: [],
            length: 1,
            selected: 0
        }
    },
    async created(){
        await this.fetchProducts();
    },
    methods: {
        goProductDetail(id){
            let productId = id;
            location.href = `/product/detail?id=${productId}`;
        },
        async fetchProducts() {
            let response;
            try {
                response = await fetch(`/api/member/orderInfo`);
                if (response.ok) {
                    let info = await response.json();
                    if (info) {
                        this.info = info;
                    } else {
                        this.info = null;
                    }
                } else {
                    this.info = null;
                }
            } catch (error) {
                this.info = null;
            }
            console.log(this.info);


            response = await fetch(`/api/member/categoryList`);
            let state = await response.json();
            this.state = state;

            response = await fetch(`/api/member/pickProductList`);
            let pickProductList = await response.json();
            this.processProducts(pickProductList);
        },
        processProducts(pickProductList) {
            const limitedProductList = pickProductList.slice(0, 9);
            limitedProductList.forEach(product => {
                this.ids.push(product.id);
                this.images.push(product.thumbnailPath+'/'+product.thumbnailName);
                this.names.push(product.name);
            });
            this.length = Math.ceil(this.images.length / 3)-1;
        },
        next() {
            if (this.selected < this.length) {
                this.selected += 1;
            }
        },
        pre() {
            if (this.selected >= 1) {
                this.selected -= 1;
            }
        }
    }
}).mount('main');
