package com.example

import com.example.db.TestEntity
import com.example.db.TestRepository
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MainApplicationKtTest {

    @Autowired
    lateinit var repository: TestRepository

    @Test
    fun `test findById returns different result between findByCode`() {
        val codeL = "code_lowercase"
        val codeU = "CODE_UPPERCASE"
        val codeC = "Code_CamelWithUnderline"

        repository.save(TestEntity(codeL))
        repository.save(TestEntity(codeU))
        repository.save(TestEntity(codeC))

        val data = repository.findAll()
        assert(data.size == 3)
        assert(data.any { it.code == codeL })
        assert(data.any { it.code == codeU })
        assert(data.any { it.code == codeC })

        val codeLFindByCode = repository.findByCode(codeL)
        val codeLToLowerFindByCode = repository.findByCode(codeL.lowercase())
        val codeLToUpperFindByCode = repository.findByCode(codeL.uppercase())
        val codeLFindById = repository.findById(codeL)
        val codeLToLowerFindById = repository.findById(codeL.lowercase())
        val codeLToUpperFindById = repository.findById(codeL.uppercase())
        val codeLFindByCodeValue = codeLFindByCode!!.code
        assert(codeLFindByCodeValue == codeLToLowerFindByCode?.code)
        assert(codeLFindByCodeValue == codeLToUpperFindByCode?.code)
        assert(codeLFindByCodeValue == codeLFindById.get().code)
        assert(codeLFindByCodeValue == codeLToLowerFindById.get().code)
        assert(codeLFindByCodeValue == codeLToUpperFindById.get().code)

        val codeUFindByCode = repository.findByCode(codeU)
        val codeUToLowerFindByCode = repository.findByCode(codeU.lowercase())
        val codeUToUpperFindByCode = repository.findByCode(codeU.uppercase())
        val codeUFindById = repository.findById(codeU)
        val codeUToLowerFindById = repository.findById(codeU.lowercase())
        val codeUToUpperFindById = repository.findById(codeU.uppercase())
        val codeUFindByCodeValue = codeUFindByCode!!.code
        assert(codeUFindByCodeValue == codeUToLowerFindByCode?.code)
        assert(codeUFindByCodeValue == codeUToUpperFindByCode?.code)
        assert(codeUFindByCodeValue == codeUFindById.get().code)
        assert(codeUFindByCodeValue == codeUToLowerFindById.get().code)
        assert(codeUFindByCodeValue == codeUToUpperFindById.get().code)

        val codeCFindByCode = repository.findByCode(codeC)
        val codeCToLowerFindByCode = repository.findByCode(codeC.lowercase())
        val codeCToUpperFindByCode = repository.findByCode(codeC.uppercase())
        val codeCFindById = repository.findById(codeC)
        val codeCToLowerFindById = repository.findById(codeC.lowercase())
        val codeCToUpperFindById = repository.findById(codeC.uppercase())
        val codeCFindByCodeValue = codeCFindByCode!!.code
        assert(codeCFindByCodeValue == codeCToLowerFindByCode?.code)
        assert(codeCFindByCodeValue == codeCToUpperFindByCode?.code)
        assert(codeCFindByCodeValue == codeCFindById.get().code)
        assert(codeCFindByCodeValue == codeCToLowerFindById.get().code)
        assert(codeCFindByCodeValue == codeCToUpperFindById.get().code)
    }
}