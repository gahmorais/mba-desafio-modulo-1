package br.com.backupautomacao.desafiombaigtimodulo1.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
  val description: String,
  val quantity: Int,
  val select: Boolean = false
) : Parcelable