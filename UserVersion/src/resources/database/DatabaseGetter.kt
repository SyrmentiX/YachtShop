package resources.database

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sun.corba.se.pept.transport.ContactInfo
import java.net.URL

class DatabaseGetter {
    fun getUserById(userID : Int) : Customers {
        val json = DatabaseSender.send(Tables.customers, userID)
        return Gson().fromJson<Customers>(json, Customers::class.java)
    }

    fun getBoats() : ArrayList<Boat> {
        val json = DatabaseSender.send(Tables.boat)
        return Gson().fromJson(json, object : TypeToken<List<Boat>>() {}.type)
    }

    fun getVat(vatID : Int) : Vat {
        val json = DatabaseSender.send(Tables.vat, vatID)
        return Gson().fromJson<Vat>(json, Vat::class.java)
    }

    fun getBoatById(id : Int) : Boat {
        val json = DatabaseSender.send(Tables.boat, id)
        return Gson().fromJson<Boat>(json, Boat::class.java)
    }

    fun getAccessoryByBoatId(boatId : Int) : ArrayList<AccessoryId> {
        val accessoryJson = DatabaseSender.send(Tables.accessoryId)
        val accessoryList : ArrayList<AccessoryId> = Gson().fromJson<ArrayList<AccessoryId>>(accessoryJson, object : TypeToken<List<AccessoryId>>() {}.type)

        val fitJson = DatabaseSender.send(Tables.fit)
        val fitList : ArrayList<Fit> = Gson().fromJson<ArrayList<Fit>>(fitJson, object : TypeToken<List<Fit>>() {}.type)

        val chosenAccessory : ArrayList<AccessoryId> = arrayListOf()
        for (fit in fitList) {
            if (fit.boatId == boatId) {
                for (accessory in accessoryList) {
                    if (accessory.accessoryId1 == fit.accessoryId) {
                        chosenAccessory.add(accessory)
                        break
                    }
                }
            }
        }
        return chosenAccessory
    }

    fun getOrdersByUserId(userID : Int) : ArrayList<Orders> {
        val url = "https://yaht.azurewebsites.net/Orders/Get?customerId=${userID}"
        val json = URL(url).openStream().bufferedReader().use{ it.readText() }
        return Gson().fromJson(json, object : TypeToken<List<Orders>>() {}.type)
    }

    fun getContractByOrderId(orderID : Int) : Contract {
        val contractJson = DatabaseSender.send(Tables.contract)
        val contractList : ArrayList<Contract> = Gson().fromJson<ArrayList<Contract>>(contractJson, object : TypeToken<List<Contract>>() {}.type)

        for (contract in contractList) {
            if (contract.orderId == orderID) {
                return contract
            }
        }
        return Contract()
    }

    fun getOrderProductionProgress(productionProcessID : Int) : ProductionProcess {
        val progressJson =
            DatabaseSender.send(Tables.productionProcess, productionProcessID)
        return Gson().fromJson<ProductionProcess>(progressJson, ProductionProcess::class.java)
    }

    fun getOrderDetail(orderID: Int) : ArrayList<Details> {
        val detailsJson = DatabaseSender.send(Tables.details)
        val detailsList : ArrayList<Details> = Gson().fromJson<ArrayList<Details>>(detailsJson, object : TypeToken<List<Details>>() {}.type)

        val ourDetail : ArrayList<Details> = arrayListOf()
        for (detail in detailsList) {
            if (detail.orderId == orderID) {
                ourDetail.add(detail)
            }
        }
        return ourDetail
    }

    fun getAccessoryByDetails(details : ArrayList<Details>) : ArrayList<AccessoryId> {
        val accessoryJson = DatabaseSender.send(Tables.accessoryId)
        val accessoryList : ArrayList<AccessoryId> = Gson().fromJson<ArrayList<AccessoryId>>(accessoryJson, object : TypeToken<List<AccessoryId>>() {}.type)

        val ourAccessory : ArrayList<AccessoryId> = arrayListOf()
        for (detail in details) {
            for (accessory in accessoryList) {
                if (detail.accessoryId == accessory.accessoryId1) {
                    ourAccessory.add(accessory)
                    break
                }
            }
        }
        return ourAccessory
    }

    fun getAvailableDocumentTypes() : ArrayList<DocumentName> {
        val documentJson = DatabaseSender.send(Tables.documentName)
        return Gson().fromJson<ArrayList<DocumentName>>(documentJson, object : TypeToken<List<DocumentName>>() {}.type)
    }

    fun getYachtColour(colourId : Int) : String {
        val colourJson = DatabaseSender.send(Tables.colours, colourId)
        val colour  = Gson().fromJson<Colours>(colourJson, Colours::class.java)
        return colour.colour
    }

    fun getYachtType(typeId : Int) : String {
        val typeJson = DatabaseSender.send(Tables.boatType, typeId)
        val type  = Gson().fromJson<BoatType>(typeJson, BoatType::class.java)
        return type.boatType1
    }

    fun getYachtWood(woodId : Int) : String {
        val woodJson = DatabaseSender.send(Tables.wood, woodId)
        val wood  = Gson().fromJson<Wood>(woodJson, Wood::class.java)
        return wood.wood1
    }

    fun getCustomerFromOrder(orders: Orders) : Customers {
        val customerJson = DatabaseSender.send(Tables.customers, orders.customerId)
        return Gson().fromJson<Customers>(customerJson, Customers::class.java)
    }

    fun getAllOrdersWithContract() : ArrayList<Pair<Orders, Contract>> {
        val contractJson = DatabaseSender.send(Tables.contract)
        val ordersJson = DatabaseSender.send(Tables.order)
        val contractList = Gson().fromJson<ArrayList<Contract>>(contractJson, object : TypeToken<List<Contract>>() {}.type)
        val ordersList = Gson().fromJson<ArrayList<Orders>>(ordersJson, object : TypeToken<List<Orders>>() {}.type)

        val ordersWithCustomers : ArrayList<Pair<Orders, Contract>> = arrayListOf()
        for (order in ordersList) {
            for (contract in contractList) {
                if (order.orderId == contract.orderId) {
                    ordersWithCustomers.add(Pair(order, contract))
                    break
                }
            }
        }
        return ordersWithCustomers
    }

    fun getAllCustomerWithAuth() : ArrayList<Pair<Customers, Auth>> {
        val customersJson = DatabaseSender.send(Tables.customers)
        val authJson = DatabaseSender.send(Tables.auth)

        val customersList = Gson().fromJson<ArrayList<Customers>>(customersJson, object : TypeToken<List<Customers>>() {}.type)
        val authList = Gson().fromJson<ArrayList<Auth>>(authJson, object : TypeToken<List<Auth>>() {}.type)

        val customerWithAuth : ArrayList<Pair<Customers, Auth>> = arrayListOf()
        for (customer in customersList) {
            for (auth in authList) {
                if (auth.customerId == customer.customerId) {
                    customerWithAuth.add(Pair(customer, auth))
                    break
                }
            }
        }
        return customerWithAuth
    }

    fun getAuthFromCustomerId(customersID: Int) : Auth {
        val authJson = DatabaseSender.send(Tables.auth)
        val authList = Gson().fromJson<ArrayList<Auth>>(authJson, object : TypeToken<List<Auth>>() {}.type)
        for (auth in authList) {
            if (auth.customerId == customersID) {
                return auth
            }
        }
        return Auth()
    }

    fun getProductionProgress() : ArrayList<ProductionProcess> {
        val productionProcessJson = DatabaseSender.send(Tables.productionProcess)
        return Gson().fromJson<ArrayList<ProductionProcess>>(productionProcessJson, object : TypeToken<List<ProductionProcess>>() {}.type)
    }
}