let baseUrl = window.location.origin;
export default class CartRepository{

    findPromise(url,method="GET"){
        console.log("fetch url : ", url);
        console.log("fetch method : ", method);

        return fetch(url,{method:method});
    }

    async findItem(prdId){
        let url = `${baseUrl}/user/api/cart/${prdId}`;

        let response = await this.findPromise(url);

        // 응답의 상태 코드 확인
        if (response.status === 200) {
            //response가 비었는지 확인
            if (response.headers.get('content-length') === '0') {
                return null;
            }
            let item = await response.json();
            return item;
        }
    }

    async add(prdId){
        let url = `${baseUrl}/user/api/cart/${prdId}`;
        let method = 'POST';
        let response = await this.findPromise(url, method);
        let vaild = await response.json();

        if (vaild) {
            return vaild;
        }
    }

    async addCount(prdId){
        let url = `${baseUrl}/user/api/cart/${prdId}?q=1`;
        let method = 'POST';
        let response = await this.findPromise(url, method);
        let vaild = await response.json();

        console.log("valid : ", vaild);

        if (vaild) {
            return vaild;
        }
    }


}

