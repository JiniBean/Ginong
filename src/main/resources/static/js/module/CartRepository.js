let baseUrl = window.location.origin;
export default class CartRepository{

    findPromise(url,method="GET", data = null){
        console.log(' 왓따' ,data)
        let header = {
            "Content-Type": "application/json",
        }

        let option = method === 'GET' ? null : {method:method, headers:header, body:data}

        return fetch(url, option);

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

    async count(){
        let url = `${baseUrl}/user/api/cart/c`;

        let response = await this.findPromise(url);

        // 응답의 상태 코드 확인
        if (response.status === 200) {
            //response가 비었는지 확인
            if (response.headers.get('content-length') === '0') {
                return null;
            }
            let count = await response.json();
            return count;
        }
    }


    async add(prdId){
        let url = `${baseUrl}/user/api/cart`;
        let method = 'POST';
        let response = await this.findPromise(url,method, prdId);
        return await response.json();
    }

    async updateQty(productId, quantity= null){
        let url = `${baseUrl}/user/api/cart/u`;
        let method = 'POST';
        let data = {productId, quantity};
        let response = await this.findPromise(url,method, JSON.stringify(data));

        return await response.json();
    }

    async delete(data){
        let url = `${baseUrl}/user/api/cart`;
        let method = 'DELETE';

        let response = await this.findPromise(url,method,data);
        return await response.json();

    }




}

