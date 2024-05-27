package org.hse.template.repository

import org.hse.template.model.Account
import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface AccountRepository : CrudRepository<Account, UUID> {

    fun findByName(name: String): Account
}
