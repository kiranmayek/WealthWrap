package com.example.amex.models

data class FInancialDataModel(
    val name:String? = null,
    val uid:String? = null,
    val email:String? = null,
    val FInancialType:String? = null,
    val noOfDependents:String? = null,
    val education:String? = null,
    val sritscore:String?=null,
    val etIncomeeAnnum:String? = null,
    val etFInancialAmount:String? = null,
    val etFInancialTerm:String? = null,
    val etCIBILScore:String? = null,
    val etResedentialAssetsValie:String? = null,
    val etCommercialAssetsValue:String? = null,
    val etLuxuryAssetsValue:String? = null,
    val etBankAsssetsValue:String? = null,
    val mlOutput:String? = null,
)
