import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.icraveviolence.NODE_AUTHORS
import com.example.icraveviolence.Product
import com.google.firebase.database.*

class ProductViewModel: ViewModel() {
    private val dbProductss = FirebaseDatabase.getInstance().getReference(NODE_AUTHORS)

    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>>
        get() = _products

    private val _product = MutableLiveData<Product>()
    val product: LiveData<Product>
        get() = _product

    private val _result = MutableLiveData<Exception?>()
    val result: LiveData<Exception?>
        get() = _result

    fun addProducts(product: Product) {
        product.productId= dbProductss.push().key
        dbProductss.child(product.productId!!).setValue(product)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    _result.value = null
                } else {
                    _result.value = it.exception
                }
            }
    }

    private val childEventListener = object : ChildEventListener {
        override fun onCancelled(error: DatabaseError) {}

        override fun onChildMoved(snapshot: DataSnapshot, p1: String?) {}

        override fun onChildChanged(snapshot: DataSnapshot, p1: String?) {
            val product = snapshot.getValue(Product::class.java)
            product?.productId = snapshot.key
            _product.value = product
        }

        override fun onChildAdded(snapshot: DataSnapshot, p1: String?) {
            val product = snapshot.getValue(Product::class.java)
            product?.productId = snapshot.key
            _product.value = product
        }

        override fun onChildRemoved(snapshot: DataSnapshot) {
            val product = snapshot.getValue(Product::class.java)
            product?.productId = snapshot.key
//            product?.isDeleted = true
            _product.value = product
        }
    }

    fun getRealtimeUpdates() {
        dbProductss.addChildEventListener(childEventListener)
    }


    fun fetchAllProducts() {
        val dbProducts =FirebaseDatabase.getInstance().getReference(NODE_AUTHORS)

        dbProductss.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {}

            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val products = mutableListOf<Product>()
                    for (productSnapshot in snapshot.children) {
                        val product = productSnapshot.getValue(Product::class.java)
                        product?.productId = productSnapshot.key
                        product?.let { products.add(it) }
                    }
                    _products.value = products
                }
            }
        })
    }


    fun fetchProducts() {
        dbProductss.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {}

            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val products = mutableListOf<Product>()
                    for (productSnapshot in snapshot.children) {
                        val product = productSnapshot.getValue(Product::class.java)
                        product?.productId = productSnapshot.key
                        product?.let { products.add(it) }
                    }
                    _products.value = products
                }
            }
        })
    }

    fun updateProducts(product: Product) {
        dbProductss.child(product.productId!!).setValue(product)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    _result.value = null
                } else {
                    _result.value = it.exception
                }
            }
    }

    fun deleteProducts(product: Product) {
        dbProductss.child(product.productId!!).setValue(null)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    _result.value = null
                } else {
                    _result.value = it.exception
                }
            }
    }

    override fun onCleared() {
        super.onCleared()
        dbProductss.removeEventListener(childEventListener)
    }
}