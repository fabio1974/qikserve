import React, { useState, useEffect } from 'react';
import logo from './logo.svg';
import axios from 'axios';


function App() {

    const [name, setName] = useState();
    const [email, setEmail] = useState();
    const [basket,setBasket] = useState();
    const [checked,setChecked] = useState(false);
    const [purchasing,setPurchasing] = useState(false);
    const [basketOptions,setBasketOptions] = useState();
    const [products, setProducts] = useState([]);
    const [productPromotions, setProductPromotions] = useState([]);
    const [selectedProduct,setSelectedProduct] = useState();
    const [quantity, setQuantity] = useState(0);
    const [output, setOutput] = useState([]);
    const [running,setRunning] = useState(false)

    const productOptions = products.map((p)=> <option key={p.id} value={p.id}>{p.name} - £{p.price.toFixed(2)}</option>)

    useEffect(async () => {
            setRunning(true);
            const result = await axios('http://localhost:8080/api/products');
            setProducts(result.data)
            setRunning(false);
    },[]);


    async function createNewBasket(){
        setRunning(true);
        let newBAsket = {user:{name,email},"items":[]}
        await setBasket(newBAsket);
        setBasketOptions([]);
        setProductPromotions([]);
        setOutput(<div></div>)
        setPurchasing(true);
        setQuantity(0);
        setSelectedProduct(null);
        const result = await axios.post('http://localhost:8080/api/baskets',{user:{name,email}});
        await setBasket(result.data);
        setRunning(false);
    }

    async function addItem(event){
        setRunning(true);
        const newItem = {"product":selectedProduct,quantity}
        let items = basket['items'];
        items.push(newItem);
        await setBasket({...basket,items});
        await setBasketOptions(basket['items'].map((item,index) => {
            return (<option key={index} value={item.id}>{item.quantity} X {products.find(p=> p.id==item.product.id).name}</option>);
        }))
        const result = await axios.post(`http://localhost:8080/api/baskets/${basket['id']}/addItem`,newItem);
        setRunning(false);
    }

    async function handleChangeProduct(evt){
        let prod = products.find(p=> p.id==evt.target.value)
        await setSelectedProduct(prod)
        await setProductPromotions(prod['promotions'].map(promo => {
            return (<option key={promo.id} value={promo.id}>{promo.type}</option>);
        }))

    }

    async function checkout(){
        setRunning(true);
        if(basket['items'].length==0) {
            alert('Add Items do the basket!')
            return;
        }

        setPurchasing(false);
        const result = await axios.get(`http://localhost:8080/api/baskets/${basket['id']}/checkout`);
        setBasket(result.data);

        setOutput(
            <div>
            <label>Receipt After Apply Promotions</label>
            <table className="table table-striped table-bordered ">
                <thead>
                <tr>
                    <td>Product</td>
                    <td>Price</td>
                    <td>Quantity</td>
                    <td>Total Price</td>
                    <td>Total Promos</td>
                    <td>Paid</td>
                </tr>
                </thead>
                <tbody>
                {
                    result.data['items'].map((item, index) => {
                        let product = products.find(p => p.id == item.product);
                        let totalItemPrice = item.quantity * product.price;
                        return (
                            <tr key={index}>
                                <td>{product.name}</td>
                                <td>£{product.price.toFixed(2)}</td>
                                <td>{item.quantity}</td>
                                <td>£{totalItemPrice.toFixed(2)}</td>
                                <td>£{item.bestPromotionValue.toFixed(2)}</td>
                                <td>£{(totalItemPrice - item.bestPromotionValue).toFixed(2)}</td>
                            </tr>
                        );
                    })
                }
                </tbody>
                <tfoot>
                <tr>
                    <td colSpan={3}>{<label>Total</label>}</td>
                    <td>{result.data.total.toFixed(2)}</td>
                    <td>{result.data.totalSaved.toFixed(2)}</td>
                    <td>{result.data.totalPayble.toFixed(2)}</td>
                </tr>
                </tfoot>
            </table>
            </div>
        );
        setRunning(false);
    }

    return (
        <div className="card">
            <div className="card-body ">
                <form>
                    <div className="row mb-4">
                      <div className="col-4 form-inline">
                          <div className="form-group">
                              <label >Name</label>
                              <input type="text" disabled={purchasing} required onChange={evt=>setName(evt.target.value)} className="form-control" id="name" />
                          </div>
                      </div>
                      <div className="col-4 form-inline">
                          <div className="form-group ">
                              <label >Email</label>
                              <input type="text" disabled={purchasing} required onChange={evt=>setEmail(evt.target.value)} className="form-control" id="email" />
                          </div>
                      </div>
                      <div className="col-4 mt-4">
                          <button type="button" disabled={running || !name || !email} onClick={createNewBasket} className="d-inline btn btn-success me-3">New Purchase</button>
                      </div>
                    </div>
                    <div className="row mb-4">
                        <div className="col-4">
                            <label>Products</label>
                            <select type="text" onChange={handleChangeProduct}  className="form-control" name="T_" size="7">
                                {productOptions}
                            </select>
                        </div>
                        <div className="col-4">
                            <label>Product Promotions</label>
                            <select type="text" className="form-control" disabled={!purchasing} name="T_" size="7">
                                {productPromotions}
                            </select>
                        </div>
                        <div className="col-4">
                            <label>Basket Content</label>
                            <select type="text" className="form-control" disabled={!purchasing} name="T_" size="7">
                                {basketOptions}
                            </select>
                        </div>
                    </div>
                    <div className="row mb-1">
                        <div className="col-12">
                            <button type="button" onClick={addItem} disabled={running || !purchasing || !selectedProduct || quantity==0}  className="d-inline btn btn-success">Add Item - Qty:</button>
                            <input type={"number"} value={quantity} disabled={!purchasing} onChange={evt => setQuantity(evt.target.value)} className="d-inline form-control me-3 w-100" />
                            <button type="button" onClick={checkout} disabled={running || !purchasing} className="d-inline btn btn-warning me-3">Checkout</button>
                        </div>
                    </div>

                    <hr/>

                    <div className="row mb-4">
                        {output}
                    </div>
                </form>
            </div>
        </div>
    );
}

export default App;
