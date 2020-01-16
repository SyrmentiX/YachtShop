package resources

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.net.URL

class DatabaseGetter {
    fun getUserById(userID : Int) : Customers {
        val json = Sender.send(Tables.customers, userID)
        return Gson().fromJson<Customers>(json, Customers::class.java)
    }

    fun getBoats() : ArrayList<Boat> {
        val json = Sender.send(Tables.boat)
        return Gson().fromJson(json, object : TypeToken<List<Boat>>() {}.type)
    }

    fun getVat(vatID : Int) : Vat {
        val json = Sender.send(Tables.vat, vatID)
        return Gson().fromJson<Vat>(json, Vat::class.java)
    }

    fun getBoatById(id : Int) : Boat {
        val json = Sender.send(Tables.boat, id)
        return Gson().fromJson<Boat>(json, Boat::class.java)
    }

    fun getAccessoryByBoatId(boatId : Int) : ArrayList<AccessoryId> {
        val accessoryJson = Sender.send(Tables.accessoryId)
        val accessoryList : ArrayList<AccessoryId> = Gson().fromJson<ArrayList<AccessoryId>>(accessoryJson, object : TypeToken<List<AccessoryId>>() {}.type)

        val fitJson = Sender.send(Tables.fit)
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
        val contractJson = Sender.send(Tables.contract)
        val contractList : ArrayList<Contract> = Gson().fromJson<ArrayList<Contract>>(contractJson, object : TypeToken<List<Contract>>() {}.type)

        for (contract in contractList) {
            if (contract.orderId == orderID) {
                return contract
            }
        }
        return Contract()
    }

    fun getOrderProductionProgress(productionProcessID : Int) : ProductionProcess {
        val progressJson = Sender.send(Tables.productionProcess, productionProcessID)
        return Gson().fromJson<ProductionProcess>(progressJson, ProductionProcess::class.java)
    }

    fun getOrderDetail(orderID: Int) : ArrayList<Details> {
        val detailsJson = Sender.send(Tables.details)
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
        val accessoryJson = Sender.send(Tables.accessoryId)
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
        val documentJson = Sender.send(Tables.documentName)
        return Gson().fromJson<ArrayList<DocumentName>>(documentJson, object : TypeToken<List<DocumentName>>() {}.type)
    }

    fun getYachtColour(colourId : Int) : String {
        val colourJson = Sender.send(Tables.colours,colourId)
        val colour  = Gson().fromJson<Colours>(colourJson, Colours::class.java)
        return colour.colour
    }

    fun getYachtType(typeId : Int) : String {
        val typeJson = Sender.send(Tables.boatType,typeId)
        val type  = Gson().fromJson<BoatType>(typeJson, BoatType::class.java)
        return type.boatType1
    }

    fun getYachtWood(woodId : Int) : String {
        val woodJson = Sender.send(Tables.wood,woodId)
        val wood  = Gson().fromJson<Wood>(woodJson, Wood::class.java)
        return wood.wood1
    }

}