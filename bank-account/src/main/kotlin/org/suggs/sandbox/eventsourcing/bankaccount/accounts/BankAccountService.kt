package org.suggs.sandbox.eventsourcing.bankaccount.accounts

import org.springframework.boot.Banner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class BankAccountService

fun main(args: Array<String>) {
    runApplication<BankAccountService>(*args) {
        setBannerMode(Banner.Mode.OFF)
    }
}
