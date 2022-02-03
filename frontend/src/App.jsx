import React, { useState, useEffect } from 'react';
import logo from './logo.svg';
import axios from 'axios';


function App() {

    const [name, setName] = useState();
    const [email, setEmail] = useState();
    const [basket,setBasket] = useState();
    const [basketOptions,setBasketOptions] = useState();
    const [products, setProducts] = useState([]);
    const [productPromotions, setProductPromotions] = useState([]);
    const [selectedProduct,setSelectedProduct] = useState();
    const [quantity, setQuantity] = useState(0);

    const productOptions = products.map((p)=> <option key={p.id} value={p.id}>{p.name}</option>)


    useEffect(async () => {
            const result = await axios('http://localhost:8080/api/products');
            console.log("result",result.data);
            setProducts(result.data)
    },[]);




    async function createNewBasket(){
        let newBAsket = {user:{name,email},"items":[]}
        await setBasket(newBAsket);
        setBasketOptions([]);
        //const result = await axios.post('http://localhost:8080/api/baskets',{user:{name,email}});
        //await setBasket(result.data);
    }

    async function addItem(event){
        const newItem = {"product":selectedProduct,quantity}
        let items = basket['items'];
        items.push(newItem);
        await setBasket({...basket,items});
        console.log("new basket",basket)
        await setBasketOptions(basket['items'].map((item,index) => {
            console.log("item", item)
            return (<option key={index} value={item.id}>{item.quantity} X {products.find(p=> p.id==item.product.id).name}</option>);
        }))

    }

    async function handleChangeProduct(evt){
        let prod = products.find(p=> p.id==evt.target.value)
        await setSelectedProduct(prod)
        await setProductPromotions(prod['promotions'].map(promo => {
            return (<option key={promo.id} value={promo.id}>{promo.type}</option>);
        }))

    }

    return (
        <div className="card">
            <div className="card-body ">

                <form>

                    <div className="row mb-4">

                      <div className="col-4 form-inline">
                          <div className="form-group">
                              <label >Name</label>
                              <input type="text" required onChange={evt=>setName(evt.target.value)} className="form-control" id="name" />
                          </div>
                      </div>

                      <div className="col-4 form-inline">
                          <div className="form-group ">
                              <label >Email</label>
                              <input type="text" required onChange={evt=>setEmail(evt.target.value)} className="form-control" id="email" />
                          </div>
                      </div>

                      <div className="col-4 mt-4">

                          <button type="button" onClick={createNewBasket} className="d-inline btn btn-success me-3">New Purchase</button>
                      </div>

                    </div>

                    <div className="row mb-4">
                        <div className="col-4">
                            <label>Products</label>
                            <select type="text" onChange={handleChangeProduct} className="form-control" name="T_" size="7">
                                {productOptions}
                            </select>
                        </div>

                        <div className="col-4">
                            <label>Product Promotions</label>
                            <select type="text" className="form-control" name="T_" size="7">
                                {productPromotions}
                            </select>
                        </div>


                        <div className="col-4">
                            <label>Basket ID={basket?.id}</label>
                            <select type="text" className="form-control" name="T_" size="7">
                                {basketOptions}
                            </select>
                        </div>


                    </div>

                    <div className="row mb-5">
                        <div className="col-12">
                            <button type="button" onClick={addItem} className="d-inline btn btn-success">Add Item - Qty:</button>
                            <input type={"number"} value={quantity} onChange={evt => setQuantity(evt.target.value)} className="d-inline form-control me-3 w-100" />
                            <button type="button" className="d-inline btn btn-danger me-3">Delete Item</button>
                            <button type="button" className="d-inline btn btn-warning me-3">Checkout</button>
                        </div>
                    </div>

                    <div className="row mb-4">
                        <div>
                        <label>Receipt After Apply Promotions</label>
                        <table className="table-bordered ">
                            <thead>
                            <tr>
                                <td>Product</td>
                                <td>Quantity</td>
                                <td>Total Price</td>
                                <td>Promotion</td>
                                <td>Paid</td>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td>Comida</td>
                                <td>3</td>
                                <td>21347</td>
                                <td>213</td>
                                <td>213</td>
                            </tr>
                            </tbody>
                        </table>
                        </div>
                    </div>

                </form>
            </div>
        </div>
    );
}

export default App;
