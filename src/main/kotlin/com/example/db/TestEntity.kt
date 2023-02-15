package com.example.db

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "testing_table")
data class TestEntity(
    @Id val code: String
)