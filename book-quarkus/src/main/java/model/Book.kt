package model

import lombok.Data

@Data
class Book(public val title: String, public val price: String, public val isbn: String, public var quantity: String)
