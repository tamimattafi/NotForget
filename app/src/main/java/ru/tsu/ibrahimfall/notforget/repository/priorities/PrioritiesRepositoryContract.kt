package ru.tsu.ibrahimfall.notforget.repository.priorities

import ru.tsu.ibrahimfall.notforget.model.items.Priority
import ru.tsu.ibrahimfall.notforget.mvp.MvpBaseContract.*

interface PrioritiesRepositoryContract {
    interface Repository : ListRepository<Priority>
}