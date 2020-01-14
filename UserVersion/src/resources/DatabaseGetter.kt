package resources

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DatabaseGetter {
    fun getBoats() : ArrayList<Boat> {
        val json = Sender.send(Tables.boat)
        return Gson().fromJson(json, object : TypeToken<List<Boat>>() {}.type)
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
}