package com.tech.bbvachallenge.presentation.viewmodel

import com.tech.domain.model.Song
import com.tech.domain.usecase.GetSongsByAlbumUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SongViewModel @Inject constructor(
    private val getSongsByAlbumUseCase: GetSongsByAlbumUseCase
) : BaseViewModelParams<Song, String>(
    fetchUseCase = { albumId -> getSongsByAlbumUseCase(albumId) }
)