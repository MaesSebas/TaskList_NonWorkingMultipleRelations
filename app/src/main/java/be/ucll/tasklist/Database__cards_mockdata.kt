package be.ucll.tasklist

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.Random

class Database__cards_mockdata {

    fun generateMockCheckingsAccounts(): MutableList<Database__C_Account> {
        val accountsMockData = mutableListOf<Database__C_Account>()

        val account1 = Database__C_Account(
            userID = 1,
            accountID = 1,
            accountName = "ING",
            accountNumber = "BE12345678",
            totalBalance = "1000.00",
            accountType = "CheckingsAccount"
        )
        accountsMockData.add(account1)

        val account2 = Database__C_Account(
            userID = 1,
            accountID = 2,
            accountName = "KBC",
            accountNumber = "BE87654321",
            totalBalance = "1500.00",
            accountType = "CheckingsAccount"
        )
        accountsMockData.add(account2)

        return accountsMockData
    }

    fun generateMockSavingsAccounts(): MutableList<Database__C_Account> {
        val accountsMockData = mutableListOf<Database__C_Account>()

        val account1 = Database__C_Account(
            userID = 1,
            accountID = 3,
            accountName = "ING",
            accountNumber = "BE12345678",
            totalBalance = "100000.00",
            accountType = "SavingsAccount"
        )
        accountsMockData.add(account1)

        val account2 = Database__C_Account(
            userID = 1,
            accountID = 4,
            accountName = "KBC",
            accountNumber = "BE87654321",
            totalBalance = "150000.00",
            accountType = "SavingsAccount"
        )
        accountsMockData.add(account2)

        return accountsMockData
    }

    fun generateMockAssetAccount(): MutableList<Database__C_Account> {
        val accountsMockData = mutableListOf<Database__C_Account>()

        val account1 = Database__C_Account(
            userID = 1,
            accountID = 7,
            accountName = "Degiro",
            accountNumber = "BE12345678",
            totalBalance = "100000.00",
            accountType = "AssetAccount"
        )
        accountsMockData.add(account1)
        return accountsMockData
    }

    fun generateMockExtraLegalAccounts(): MutableList<Database__C_Account> {
        val accountsMockData = mutableListOf<Database__C_Account>()

        val account1 = Database__C_Account(
            userID = 1,
            accountID = 5,
            accountName = "Edenred",
            accountNumber = "12345678",
            totalBalance = "190.00",
            accountType = "ExtraLegalAccount"
        )
        accountsMockData.add(account1)

        val account2 = Database__C_Account(
            userID = 1,
            accountID = 6,
            accountName = "Monizze",
            accountNumber = "87654321",
            totalBalance = "250.00",
            accountType = "ExtraLegalAccount"
        )
        accountsMockData.add(account2)

        return accountsMockData
    }

    fun generateAssetMockDataAssetAccounts(): MutableList<Database__C_Asset> {
        val mockDataList = mutableListOf<Database__C_Asset>()

        val stockTickers = listOf("AAPL", "GOOGL", "MSFT", "AMZN", "TSLA", "FB", "V", "NVDA", "PYPL", "NFLX")
        mockDataList.addAll(generateMockDataForType("Stocks", 9, stockTickers))

        val etfTickers = listOf("SPY", "QQQ", "VTI", "IWM", "EFA", "EEM", "GLD", "SLV", "VWO", "VNQ")
        mockDataList.addAll(generateMockDataForType("ETFs", 9, etfTickers))

        val cryptoTickers = listOf("BTC", "ETH", "XRP", "LTC", "BCH", "ADA", "DOT", "LINK", "XLM", "DOGE")
        mockDataList.addAll(generateMockDataForType("Cryptos", 9, cryptoTickers))

        val obligationTickers = listOf("GOVT", "AGG", "BND", "LQD", "HYG", "TLT", "IEF", "MUB", "MINT", "BOND")
        mockDataList.addAll(generateMockDataForType("Obligations", 9, obligationTickers))

        return mockDataList
    }

    private fun generateMockDataForType(type: String, count: Int, tickers: List<String>): List<Database__C_Asset> {
        val dataList = mutableListOf<Database__C_Asset>()
        val random = Random()

        for (i in 1..count) {
            val dateFormat = SimpleDateFormat("MM/dd/yyyy", Locale.US)
            val investment = Database__C_Asset(
                userID = 1,
                investmentId = random.nextLong(),
                accountID = 7,
                investmentType = type,
                name = "$type",
                ticker = tickers[i],
                quantity = (random.nextDouble() * (10.0 - 1.0) + 1.0).toInt(),
                lastValue = random.nextDouble() * (1000.0 - 1.0) + 1.0,
                lastUpdated = dateFormat.parse("2/12/2023").toString(),
            )
            dataList.add(investment)
        }

        return dataList
    }

    fun generateMockTransactionData(investments: List<Database__C_Asset>): List<Database__C_AssetTransaction> {
        val mockData = mutableListOf<Database__C_AssetTransaction>()
        val random = Random()
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

        for(investment in investments) {
            for (i in 1..3) {
                val transactionType = if (random.nextBoolean()) "Buy" else "Sell"
                val quantity = random.nextInt(15) + 1
                val dateBuy = dateFormat.format(Date())

                val calendar = Calendar.getInstance()
                calendar.time = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(dateBuy)
                calendar.add(Calendar.DAY_OF_MONTH, random.nextInt(30) + 1) // Add 1 to 30 days
                dateFormat.format(calendar.time)

                val dateSell = dateFormat

                val valueBuy = random.nextDouble() * 999 + 1

                val mockTransaction = Database__C_AssetTransaction(
                    investmentId = investment.investmentId,
                    transactionType = transactionType,
                    quantity = quantity,
                    transactionDate = dateSell.toString(),
                    value = valueBuy,
                )

                mockData.add(mockTransaction)
            }
        }

        return mockData
    }

    fun generateTransactionsMockDataCheckingsAccounts(): MutableList<Database__C_Transaction> {
        val transactions = mutableListOf<Database__C_Transaction>()

        val dateFormatter = SimpleDateFormat("yyyy-MM-dd")
        val random = Random()

        val companies = listOf(
            "Acme Electronics", "Bright Foods", "Quantum Motors", "SolarTech Solutions", "GreenWave Energy",
            "AquaHealth Labs", "Swift Couriers", "UrbanStyle Apparel", "EcoGrocery Mart", "TechVibe Innovations",
            "FusionFitness Studios", "FreshFlare Florists", "QuickBite Eateries", "Stellar Stationery", "Zenith Pharmaceuticals",
            "SunSip Beverages", "BlazeGear Sports", "UrbanCraft Furniture", "ByteTech Computers", "PurePlates Kitchen",
            "NatureNook Nursery", "GigaGrocery", "MetroMingle Events", "PetalPush Floral", "Skyward Airlines",
            "EcoFuel Energy", "WellFit Pharmacy", "UrbanBlend Coffee", "SparkPlug Auto Parts", "BioBites Organics",
            "SummitStyles Outfitters", "CosmicConnect Telecom", "FreshFeast Catering", "BioTech Health", "SolarFlame Appliances",
            "GearUp Gadgets", "QuickFix Repairs", "AquaTreat Water", "SwiftShine Cleaners", "VivaVideo Productions",
            "ZenZone Yoga", "GreenWheel Transport", "PixelPlay Games", "PurePrint Media", "AquaBrew Coffee",
            "QuantumQuill Stationery", "UrbanHarbor Cruises", "FreshFuel Grocers", "SparkSprint Telecom", "BioBloom Florists",
            "SkySavor Airlines", "UrbanSculpt Furniture", "SunCraft Solar", "ByteBite Tech", "QuantumQuench Beverages",
            "BlazeFit Athletics", "EcoElite Fashion", "AquaGro Gardens", "SwiftServe Catering", "BioBreeze Health",
            "StellarSound Electronics", "SolarSip Drinks", "UrbanRide Cabs", "GigaGear Gadgets", "FreshFurnish Home",
            "GreenGen Energy", "ByteBrew Coffee", "BlazeTech Auto Parts", "AquaCraft Plumbing", "QuantumQuick Repairs",
            "UrbanGrocery Mart", "FreshFlair Fashion", "Skyward Solar", "TechTide Innovations", "GreenFuel Energy",
            "SwiftSip Beverages", "BioBlast Health", "BlazeBite Sports", "AquaStyle Apparel", "QuantumQuill Books", "Acme Electronics"
        )

        val desciptions = listOf(
            "Smartphones", "Fresh produce", "Car accessories", "Solar panels", "Renewable energy plans",
            "Bottled water", "Courier services", "Clothing", "Organic groceries", "Laptops",
            "Gym memberships", "Bouquets", "Fast food", "Office supplies", "Medications",
            "Soft drinks", "Sportswear", "Furniture", "Computer accessories", "Dinnerware",
            "Plants", "Bulk groceries", "Event planning services", "Floral arrangements", "Airline tickets",
            "Biofuel", "Health supplements", "Coffee beans", "Auto parts", "Organic snacks",
            "Clothing", "Phone plans", "Catering services", "Biotech products", "Home appliances",
            "Gadgets", "Repair services", "Water filters", "Cleaning services", "Video production services",
            "Yoga classes", "Transportation services", "Video games", "Printing services", "Coffee",
            "Stationery", "Cruise tickets", "Fuel for vehicles", "Telecommunications", "Flower seeds",
            "Airline services", "Sculptures", "Solar-powered gadgets", "Tech gadgets", "Beverages",
            "Athletic gear", "Eco-friendly clothing", "Garden supplies", "Catering services", "Health products",
            "Audio equipment", "Solar-powered drinks cooler", "Ride-hailing services", "Gadgets", "Home furnishings",
            "Green energy plans", "Coffee beans", "Auto parts", "Plumbing services", "Quick repair services",
            "Urban groceries", "Fashion items", "Solar panels", "Innovative tech products", "Green fuel",
            "Beverages", "Health supplements", "Sports equipment", "Water-resistant clothing", "Books", "Smartphones"
        )

        for (i in 1..80) {
            val transactionDate = dateFormatter.format(Date(System.currentTimeMillis() - (i * 86400000L))) // Generate dates for the past 80 days
            val isDeposit = random.nextBoolean()
            val type = if (isDeposit) "Deposit" else "Expense"

            val transaction = Database__C_Transaction(
                userID = 1,
                accountID = 1,
                companyName = companies[i],
                description = desciptions[i],
                transactionDate = transactionDate,
                category = "Category $i",
                amount = Math.round(1.0 + random.nextDouble() * 99.0).toDouble(),
                type = type
            )

            transactions.add(transaction)
        }

        for (i in 1..80) {
            val transactionDate = dateFormatter.format(Date(System.currentTimeMillis() - (i * 86400000L))) // Generate dates for the past 80 days
            val isDeposit = random.nextBoolean()
            val type = if (isDeposit) "Deposit" else "Expense"

            val transaction = Database__C_Transaction(
                userID = 1,
                accountID = 2,
                companyName = companies[i],
                description = desciptions[i],
                transactionDate = transactionDate,
                category = "Category $i",
                amount = Math.round(1.0 + random.nextDouble() * 99.0).toDouble(),
                type = type
            )

            transactions.add(transaction)
        }

        return transactions
    }

    fun generateTransactionsMockDataSavingsAccounts(): MutableList<Database__C_Transaction> {
        val transactions = mutableListOf<Database__C_Transaction>()

        val dateFormatter = SimpleDateFormat("yyyy-MM-dd")
        val random = Random()

        val companies = listOf(
            "Acme Electronics", "Bright Foods", "Quantum Motors", "SolarTech Solutions", "GreenWave Energy",
            "AquaHealth Labs", "Swift Couriers", "UrbanStyle Apparel", "EcoGrocery Mart", "TechVibe Innovations",
            "FusionFitness Studios", "FreshFlare Florists", "QuickBite Eateries", "Stellar Stationery", "Zenith Pharmaceuticals",
            "SunSip Beverages", "BlazeGear Sports", "UrbanCraft Furniture", "ByteTech Computers", "PurePlates Kitchen",
            "NatureNook Nursery", "GigaGrocery", "MetroMingle Events", "PetalPush Floral", "Skyward Airlines",
            "EcoFuel Energy", "WellFit Pharmacy", "UrbanBlend Coffee", "SparkPlug Auto Parts", "BioBites Organics",
            "SummitStyles Outfitters", "CosmicConnect Telecom", "FreshFeast Catering", "BioTech Health", "SolarFlame Appliances",
            "GearUp Gadgets", "QuickFix Repairs", "AquaTreat Water", "SwiftShine Cleaners", "VivaVideo Productions",
            "ZenZone Yoga", "GreenWheel Transport", "PixelPlay Games", "PurePrint Media", "AquaBrew Coffee",
            "QuantumQuill Stationery", "UrbanHarbor Cruises", "FreshFuel Grocers", "SparkSprint Telecom", "BioBloom Florists",
            "SkySavor Airlines", "UrbanSculpt Furniture", "SunCraft Solar", "ByteBite Tech", "QuantumQuench Beverages",
            "BlazeFit Athletics", "EcoElite Fashion", "AquaGro Gardens", "SwiftServe Catering", "BioBreeze Health",
            "StellarSound Electronics", "SolarSip Drinks", "UrbanRide Cabs", "GigaGear Gadgets", "FreshFurnish Home",
            "GreenGen Energy", "ByteBrew Coffee", "BlazeTech Auto Parts", "AquaCraft Plumbing", "QuantumQuick Repairs",
            "UrbanGrocery Mart", "FreshFlair Fashion", "Skyward Solar", "TechTide Innovations", "GreenFuel Energy",
            "SwiftSip Beverages", "BioBlast Health", "BlazeBite Sports", "AquaStyle Apparel", "QuantumQuill Books", "Acme Electronics"
        )

        val desciptions = listOf(
            "Smartphones", "Fresh produce", "Car accessories", "Solar panels", "Renewable energy plans",
            "Bottled water", "Courier services", "Clothing", "Organic groceries", "Laptops",
            "Gym memberships", "Bouquets", "Fast food", "Office supplies", "Medications",
            "Soft drinks", "Sportswear", "Furniture", "Computer accessories", "Dinnerware",
            "Plants", "Bulk groceries", "Event planning services", "Floral arrangements", "Airline tickets",
            "Biofuel", "Health supplements", "Coffee beans", "Auto parts", "Organic snacks",
            "Clothing", "Phone plans", "Catering services", "Biotech products", "Home appliances",
            "Gadgets", "Repair services", "Water filters", "Cleaning services", "Video production services",
            "Yoga classes", "Transportation services", "Video games", "Printing services", "Coffee",
            "Stationery", "Cruise tickets", "Fuel for vehicles", "Telecommunications", "Flower seeds",
            "Airline services", "Sculptures", "Solar-powered gadgets", "Tech gadgets", "Beverages",
            "Athletic gear", "Eco-friendly clothing", "Garden supplies", "Catering services", "Health products",
            "Audio equipment", "Solar-powered drinks cooler", "Ride-hailing services", "Gadgets", "Home furnishings",
            "Green energy plans", "Coffee beans", "Auto parts", "Plumbing services", "Quick repair services",
            "Urban groceries", "Fashion items", "Solar panels", "Innovative tech products", "Green fuel",
            "Beverages", "Health supplements", "Sports equipment", "Water-resistant clothing", "Books", "Smartphones"
        )

        for (i in 1..80) {
            val transactionDate = dateFormatter.format(Date(System.currentTimeMillis() - (i * 86400000L))) // Generate dates for the past 80 days
            val isDeposit = random.nextBoolean()
            val type = if (isDeposit) "Deposit" else "Expense"

            val transaction = Database__C_Transaction(
                userID = 1,
                accountID = 3,
                companyName = companies[i],
                description = desciptions[i],
                transactionDate = transactionDate,
                category = "Category $i",
                amount = Math.round(1.0 + random.nextDouble() * 99.0).toDouble(),
                type = type
            )

            transactions.add(transaction)
        }

        for (i in 1..80) {
            val transactionDate = dateFormatter.format(Date(System.currentTimeMillis() - (i * 86400000L))) // Generate dates for the past 80 days
            val isDeposit = random.nextBoolean()
            val type = if (isDeposit) "Deposit" else "Expense"

            val transaction = Database__C_Transaction(
                userID = 1,
                accountID = 4,
                companyName = companies[i],
                description = desciptions[i],
                transactionDate = transactionDate,
                category = "Category $i",
                amount = Math.round(1.0 + random.nextDouble() * 99.0).toDouble(),
                type = type
            )

            transactions.add(transaction)
        }

        return transactions
    }

    fun generateTransactionsMockDataExtraLegalAccounts(): MutableList<Database__C_Transaction> {
        val transactions = mutableListOf<Database__C_Transaction>()

        val dateFormatter = SimpleDateFormat("yyyy-MM-dd")
        val random = Random()

        val companies = listOf(
            "Acme Electronics", "Bright Foods", "Quantum Motors", "SolarTech Solutions", "GreenWave Energy",
            "AquaHealth Labs", "Swift Couriers", "UrbanStyle Apparel", "EcoGrocery Mart", "TechVibe Innovations",
            "FusionFitness Studios", "FreshFlare Florists", "QuickBite Eateries", "Stellar Stationery", "Zenith Pharmaceuticals",
            "SunSip Beverages", "BlazeGear Sports", "UrbanCraft Furniture", "ByteTech Computers", "PurePlates Kitchen",
            "NatureNook Nursery", "GigaGrocery", "MetroMingle Events", "PetalPush Floral", "Skyward Airlines",
            "EcoFuel Energy", "WellFit Pharmacy", "UrbanBlend Coffee", "SparkPlug Auto Parts", "BioBites Organics",
            "SummitStyles Outfitters", "CosmicConnect Telecom", "FreshFeast Catering", "BioTech Health", "SolarFlame Appliances",
            "GearUp Gadgets", "QuickFix Repairs", "AquaTreat Water", "SwiftShine Cleaners", "VivaVideo Productions",
            "ZenZone Yoga", "GreenWheel Transport", "PixelPlay Games", "PurePrint Media", "AquaBrew Coffee",
            "QuantumQuill Stationery", "UrbanHarbor Cruises", "FreshFuel Grocers", "SparkSprint Telecom", "BioBloom Florists",
            "SkySavor Airlines", "UrbanSculpt Furniture", "SunCraft Solar", "ByteBite Tech", "QuantumQuench Beverages",
            "BlazeFit Athletics", "EcoElite Fashion", "AquaGro Gardens", "SwiftServe Catering", "BioBreeze Health",
            "StellarSound Electronics", "SolarSip Drinks", "UrbanRide Cabs", "GigaGear Gadgets", "FreshFurnish Home",
            "GreenGen Energy", "ByteBrew Coffee", "BlazeTech Auto Parts", "AquaCraft Plumbing", "QuantumQuick Repairs",
            "UrbanGrocery Mart", "FreshFlair Fashion", "Skyward Solar", "TechTide Innovations", "GreenFuel Energy",
            "SwiftSip Beverages", "BioBlast Health", "BlazeBite Sports", "AquaStyle Apparel", "QuantumQuill Books", "Acme Electronics"
        )

        val desciptions = listOf(
            "Smartphones", "Fresh produce", "Car accessories", "Solar panels", "Renewable energy plans",
            "Bottled water", "Courier services", "Clothing", "Organic groceries", "Laptops",
            "Gym memberships", "Bouquets", "Fast food", "Office supplies", "Medications",
            "Soft drinks", "Sportswear", "Furniture", "Computer accessories", "Dinnerware",
            "Plants", "Bulk groceries", "Event planning services", "Floral arrangements", "Airline tickets",
            "Biofuel", "Health supplements", "Coffee beans", "Auto parts", "Organic snacks",
            "Clothing", "Phone plans", "Catering services", "Biotech products", "Home appliances",
            "Gadgets", "Repair services", "Water filters", "Cleaning services", "Video production services",
            "Yoga classes", "Transportation services", "Video games", "Printing services", "Coffee",
            "Stationery", "Cruise tickets", "Fuel for vehicles", "Telecommunications", "Flower seeds",
            "Airline services", "Sculptures", "Solar-powered gadgets", "Tech gadgets", "Beverages",
            "Athletic gear", "Eco-friendly clothing", "Garden supplies", "Catering services", "Health products",
            "Audio equipment", "Solar-powered drinks cooler", "Ride-hailing services", "Gadgets", "Home furnishings",
            "Green energy plans", "Coffee beans", "Auto parts", "Plumbing services", "Quick repair services",
            "Urban groceries", "Fashion items", "Solar panels", "Innovative tech products", "Green fuel",
            "Beverages", "Health supplements", "Sports equipment", "Water-resistant clothing", "Books", "Smartphones"
        )

        for (i in 1..80) {
            val transactionDate = dateFormatter.format(Date(System.currentTimeMillis() - (i * 86400000L))) // Generate dates for the past 80 days
            val isDeposit = random.nextBoolean()
            val type = if (isDeposit) "Deposit" else "Expense"

            val transaction = Database__C_Transaction(
                userID = 1,
                accountID = 5,
                companyName = companies[i],
                description = desciptions[i],
                transactionDate = transactionDate,
                category = "Category $i",
                amount = Math.round(1.0 + random.nextDouble() * 99.0).toDouble(),
                type = type
            )

            transactions.add(transaction)
        }

        for (i in 1..80) {
            val transactionDate = dateFormatter.format(Date(System.currentTimeMillis() - (i * 86400000L))) // Generate dates for the past 80 days
            val isDeposit = random.nextBoolean()
            val type = if (isDeposit) "Deposit" else "Expense"

            val transaction = Database__C_Transaction(
                userID = 1,
                accountID = 6,
                companyName = companies[i],
                description = desciptions[i],
                transactionDate = transactionDate,
                category = "Category $i",
                amount = Math.round(1.0 + random.nextDouble() * 99.0).toDouble(),
                type = type
            )

            transactions.add(transaction)
        }

        return transactions
    }
}