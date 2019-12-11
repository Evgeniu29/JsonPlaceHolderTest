package com.paad.testtask;

public interface TransferBetweenFragments {
    void goFromUserToPost(int userID);
    void goFromPostToPost(int postID);
    void goFromCommentToComment(int commentID);
    void goFromAlbumToAlbum(int albumId);
    void goFromPhotoToPhoto(int photoId);
}
