@org.hibernate.annotations.GenericGenerator(
        name = "ID_GENERATOR",
        strategy = "enhanced-sequence",
        parameters = {
                @org.hibernate.annotations.Parameter(
                        name = "squence_name",
                        value = "GLOBAL_SEQ"
                ),
                @org.hibernate.annotations.Parameter(
                        name = "initial_value",
                        value = "100000"
                )
        })
package ru.topjava.lunchvote.model;