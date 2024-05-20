let baseUrl = window.location.origin;
export default class PointRepository{

    findPromise(url,method="GET", data = null){

        let header = {
            "Content-Type": "application/json",
        }

        // 메소드가 GET이 아니라면 option 넣음
        let option = method === 'GET' ? null : {method:method, headers:header, body:data}

        return fetch(url, option);

    }

    async findAll(query,sortType,isUser){
        let url = `${baseUrl}/api/point/list?`;

        if(query)
            url += `q=${query}&`

        if(sortType)
            url += `s=${sortType}&`

        if(isUser)
            url += `u=${isUser}`

        let response = await this.findPromise(url);
        return await response.json();
    }

    // async findCancelAll(query,isUser){
    //
    //     let url = `${baseUrl}/api/order/all?`;
    //
    //     if(query)
    //         url += `q=${query}&`
    //     if(isUser)
    //         url += `u=${isUser}`
    //
    //     let response = await this.findPromise(url);
    //     return await response.json();
    // }
    //
    // async findCancel(query,isUser){
    //
    //     let url = `${baseUrl}/api/order/cncl?`;
    //
    //     if(query)
    //         url += `q=${query}&`
    //     if(isUser)
    //         url += `u=${isUser}`
    //
    //     let response = await this.findPromise(url);
    //     return await response.json();
    // }
    //
    // async findExRef(query,sortType,isUser,idx){
    //     let url = `${baseUrl}/api/order/exrf?`;
    //
    //     if(query)
    //         url += `q=${query}&`
    //
    //     if(sortType)
    //         url += `s=${sortType}&`
    //
    //     if(isUser)
    //         url += `u=${isUser}&`
    //     if(idx==1)
    //         url += 'e=true' //교환
    //     if(idx==2)
    //         url += 'r=true' //환불
    //     if(idx==3)
    //         url += 'e=true&r=true'
    //
    //     console.log(url)
    //     let response = await this.findPromise(url);
    //     return await response.json();
    // }
    //
    // async findItems(id){
    //     let url = `${baseUrl}/api/order/${id}`;
    //
    //
    //     let response = await this.findPromise(url);
    //     return await response.json();
    // }
    //
    // async findCategories(){
    //     let url = `${baseUrl}/api/order/c`;
    //
    //
    //     let response = await this.findPromise(url);
    //     return await response.json();
    // }
    //
    // async findPayment(orderId){
    //     let url = `${baseUrl}/api/order/p?o=${orderId}`;
    //
    //     let response = await this.findPromise(url);
    //     return await response.json();
    // }
    //
    // async findLocation(orderId){
    //     let url = `${baseUrl}/api/order/l?o=${orderId}`;
    //
    //     let response = await this.findPromise(url);
    //     return await response.json();
    // }
    //
    //
    // async updateState(order){
    //     let url = `${baseUrl}/api/order/u`;
    //     let method = 'POST';
    //     let data = order;
    //     let response = await this.findPromise(url,method, JSON.stringify(data));
    //
    //     return await response.json();
    // }

}

