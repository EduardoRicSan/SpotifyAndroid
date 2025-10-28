package com.tech.bbvachallenge.presentation.viewmodel

import com.tech.domain.model.Album
import com.tech.domain.usecase.GetAlbumsByArtistUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AlbumViewModel @Inject constructor(
    private val getAlbumsUseCase: GetAlbumsByArtistUseCase
) : BaseViewModelParams<Album, String>( // el ID del artista es String
    fetchUseCase = { artistId -> getAlbumsUseCase(artistId) }
)