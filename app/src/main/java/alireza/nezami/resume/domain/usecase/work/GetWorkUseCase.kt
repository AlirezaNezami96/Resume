package alireza.nezami.resume.domain.usecase.work

import alireza.nezami.resume.domain.model.Career
import alireza.nezami.resume.domain.model.Works

/**
 * Created by Alireza Nezami on 3/19/2022.
 */
interface GetWorkUseCase {
    suspend operator fun invoke(): Result<Works>
}