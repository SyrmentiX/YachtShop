package resources.database

import resources.Yacht

class DatabaseClassParser {
    fun createUserOrder(yacht: Yacht, user : Customers) : Orders {
        val order = Orders()
        order.customerId = user.customerId
        order.deliveryAddress = user.address
        order.city = user.city
        order.boatId = yacht.id
        return order
    }

    fun createOrderContract(yacht: Yacht, orderID : Int) : Contract {
        val contract = Contract()
        contract.contractTotalPrice = yacht.price
        contract.contractTotalPriceIncVat = yacht.priceWithVat
        contract.dateDepositPayed = 1
        contract.orderId = orderID
        return contract
    }

    fun createOrderDetails(yacht: Yacht, orderID: Int) : ArrayList<Details> {
        val details : ArrayList<Details> = arrayListOf()
        for (accessory in yacht.selectedAccessory) {
            val detail = Details()
            detail.accessoryId = accessory.accessoryId1
            detail.orderId = orderID
            details.add(detail)
        }
        return details
    }
}