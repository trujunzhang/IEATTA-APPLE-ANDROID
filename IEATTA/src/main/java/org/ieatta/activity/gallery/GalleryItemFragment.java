package org.ieatta.activity.gallery;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.Animatable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.samples.zoomable.ZoomableDraweeView;

import org.w3c.dom.Text;
import org.wikipedia.util.FeedbackUtil;
import org.wikipedia.util.FileUtil;
import org.wikipedia.util.PermissionUtil;
import org.wikipedia.views.ViewUtil;

import bolts.Continuation;
import bolts.Task;

public class GalleryItemFragment extends Fragment {
    public static final String TAG = "GalleryItemFragment";
    public static final String ARG_PAGETITLE = "pageTitle";
    public static final String ARG_MEDIATITLE = "imageTitle";
    public static final String ARG_MIMETYPE = "mimeType";
    private static final int WRITE_EXTERNAL_STORAGE_PERMISSION_REQUEST = 44;

//
//    private IEAApp app;
//    private GalleryActivity parentActivity;
//    private PageTitle pageTitle;
//    private PageTitle imageTitle;
//    private String mimeType;
//
//    private ZoomableDraweeView imageView;
//
//    private View videoContainer;
//    private SimpleDraweeView videoThumbnail;
//
//    private GalleryItem galleryItem;
//
//    public GalleryItem getGalleryItem() {
//        return galleryItem;
//    }
//
//    public static GalleryItemFragment newInstance(PageTitle pageTitle, GalleryItem galleryItemProto) {
//        GalleryItemFragment f = new GalleryItemFragment();
//        Bundle args = new Bundle();
//        args.putParcelable(ARG_PAGETITLE, pageTitle);
//        args.putParcelable(ARG_MEDIATITLE, new PageTitle(galleryItemProto.getUUID(), galleryItemProto.getThumbUrl(),null));
//        args.putString(ARG_MIMETYPE, galleryItemProto.getMimeType());
//        f.setArguments(args);
//        return f;
//    }
//
//    public GalleryItemFragment() {
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        app = IEAApp.getInstance();
//        pageTitle = getArguments().getParcelable(ARG_PAGETITLE);
//        imageTitle = getArguments().getParcelable(ARG_MEDIATITLE);
//        mimeType = getArguments().getString(ARG_MIMETYPE);
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View rootView = inflater.inflate(R.layout.fragment_gallery_item, container, false);
////        videoContainer = rootView.findViewById(R.id.gallery_video_container);
////        videoThumbnail = (SimpleDraweeView) rootView.findViewById(R.id.gallery_video_thumbnail);
//        imageView = (ZoomableDraweeView) rootView.findViewById(R.id.gallery_image);
//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                parentActivity.toggleControls();
//            }
//        });
//        GenericDraweeHierarchy hierarchy = new GenericDraweeHierarchyBuilder(getResources())
//                .setActualImageScaleType(ScalingUtils.ScaleType.FIT_CENTER)
//                .build();
//        imageView.setHierarchy(hierarchy);
//        return rootView;
//    }
//
//    @Override
//    public void onActivityCreated(Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        setHasOptionsMenu(true);
//        parentActivity = (GalleryActivity) getActivity();
//        // do we already have a prepopulated item in the gallery cache?
//        galleryItem = parentActivity.getGalleryCache().get(imageTitle);
//        if (galleryItem == null) {
//            loadGalleryItem();
//        } else {
//            loadMedia();
//        }
//    }
//
//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        if (!isAdded()) {
//            return;
//        }
//        inflater.inflate(R.menu.menu_gallery, menu);
//    }
//
//    @Override
//    public void onPrepareOptionsMenu(Menu menu) {
//        super.onPrepareOptionsMenu(menu);
//        if (!isAdded()) {
//            return;
//        }
//        menu.findItem(R.id.menu_gallery_visit_page).setEnabled(galleryItem != null);
//        menu.findItem(R.id.menu_gallery_share).setEnabled(galleryItem != null
//                && imageView.getDrawable() != null);
//        menu.findItem(R.id.menu_gallery_save).setEnabled(galleryItem != null
//                && imageView.getDrawable() != null);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.menu_gallery_visit_page:
//                if (galleryItem != null) {
//                    parentActivity.finishWithPageResult(imageTitle);
//                }
//                return true;
//            case R.id.menu_gallery_save:
//                checkPermissionsToSaveImage();
//                return true;
//            case R.id.menu_gallery_share:
//                shareImage();
//                return true;
//            default:
//                break;
//        }
//        return super.onOptionsItemSelected(item);
//    }
//
//    /**
//     * Notifies this fragment that the current position of its containing ViewPager has changed.
//     *
//     * @param fragmentPosition This fragment's position in the ViewPager.
//     * @param pagerPosition    The pager's current position that is displayed to the user.
//     */
//    public void onUpdatePosition(int fragmentPosition, int pagerPosition) {
//        if (fragmentPosition != pagerPosition) {
//            // update stuff if our position is not "current" within the ViewPager...
//        } else {
//            // update stuff if our position is "current"
//        }
//    }
//
//    /**
//     * Perform a network request to load information and metadata for our gallery item.
//     */
//    private void loadGalleryItem() {
//        String thumbUrl = this.imageTitle.getThumbUrl();
//        final LeadImage leadImage = new LeadImage(thumbUrl);
//
//        leadImage.getLocalUrlTask().onSuccessTask(new Continuation<String, Task<String>>() {
//            @Override
//            public Task<String> then(Task<String> task) throws Exception {
//                GalleryItemFragment.this.loadImage(task.getResult());
//                return leadImage.getOnlineUrlTask();
//            }
//        }, Task.UI_THREAD_EXECUTOR).onSuccess(new Continuation<String, Void>() {
//            @Override
//            public Void then(Task<String> task) throws Exception {
//                String onlineUrl = task.getResult();
//                if (TextUtils.isEmpty(onlineUrl) == false) {
//                    GalleryItemFragment.this.loadImage(onlineUrl);
//                }
//                return null;
//            }
//        }, Task.UI_THREAD_EXECUTOR);
//
////        String uuid = this.imageTitle.getUUID();
////        String usedRef = this.pageTitle.getUUID();
////        DBPhoto photo = new DBPhoto();
////        photo.setUUID(uuid);
////        photo.setUsedRef(usedRef);
//////        photo.setObjectCreatedDate(this.imageTitle.);
////        ThumbnailImageUtil.sharedInstance.getCacheImageUrl(photo);
//
////        new GalleryItemFetchTask(app.getAPIForSite(pageTitle.getSite()),
////                pageTitle.getSite(), imageTitle, FileUtil.isVideo(mimeType)) {
////            @Override
////            public void onFinish(Map<PageTitle, GalleryItem> result) {
////                if (!isAdded()) {
////                    return;
////                }
////                if (result.size() > 0) {
////                    galleryItem = (GalleryItem) result.values().toArray()[0];
////                    parentActivity.getGalleryCache().put((PageTitle) result.keySet().toArray()[0],
////                            galleryItem);
////                    loadMedia();
////                } else {
////                    updateProgressBar(false, true, 0);
////                    FeedbackUtil.showMessage(getActivity(), R.string.error_network_error);
////                }
////            }
////
////            @Override
////            public void onCatch(Throwable caught) {
////                Log.e("Wikipedia", "caught " + caught.getMessage());
////                if (!isAdded()) {
////                    return;
////                }
////                updateProgressBar(false, true, 0);
////                FeedbackUtil.showError(getActivity(), caught);
////            }
////        }.execute();
//    }
//
//    /**
//     * Load the actual media associated with our gallery item into the UI.
//     */
//    private void loadMedia() {
//        if (FileUtil.isVideo(mimeType)) {
//            loadVideo();
//        } else {
//            // it's actually OK to use the thumbUrl in all cases, and here's why:
//            // - in the case of a JPG or PNG image:
//            //     - if the image is bigger than the requested thumbnail size, then the
//            //       thumbnail will correctly be a scaled-down version of the image, so
//            //       that it won't overload the device's bitmap buffer.
//            //     - if the image is smaller than the requested thumbnail size, then the
//            //       thumbnail url will be the same as the actual image url.
//            // - in the case of an SVG file:
//            //     - we need the thumbnail image anyway, since the ImageView can't
//            //       display SVGs.
//            loadImage(galleryItem.getThumbUrl());
//        }
//
//        parentActivity.supportInvalidateOptionsMenu();
//        parentActivity.layoutGalleryDescription();
//    }
//
//    private View.OnClickListener videoThumbnailClickListener = new View.OnClickListener() {
//        private boolean loading = false;
//
//        @Override
//        public void onClick(View v) {
//
//        }
//    };
//
//    private void loadVideo() {
//
//    }
//
//    private void loadImage(LeadImage leadImage) {
//        imageView.setVisibility(View.VISIBLE);
//        ViewUtil.loadMultiImageUrlInto(this.imageView, leadImage.getLocalUrl(), leadImage.getOnlineUrl());
//    }
//
//    private void loadImage(String url) {
//        imageView.setVisibility(View.VISIBLE);
//        Log.d(TAG, "Loading image from url: " + url);
//
//        AbstractDraweeController build = Fresco.newDraweeControllerBuilder()
//                .setUri(url)
//                .setAutoPlayAnimations(true)
//                .setOldController(imageView.getController())
//                .setControllerListener(new BaseControllerListener<ImageInfo>() {
//                    @Override
//                    public void onFinalImageSet(String id, ImageInfo imageInfo, Animatable animatable) {
//                        if (!isAdded()) {
//                            return;
//                        }
//                        if (shouldHaveWhiteBackground(galleryItem.getMimeType())) {
//                            imageView.setBackgroundColor(Color.WHITE);
//                        }
//                        parentActivity.supportInvalidateOptionsMenu();
//                    }
//
//                    @Override
//                    public void onFailure(String id, Throwable throwable) {
//                        if (!isAdded()) {
//                            return;
//                        }
//                        FeedbackUtil.showMessage(getActivity(), R.string.gallery_error_draw_failed);
//                    }
//                })
//                .build();
//        imageView.setController(build);
//    }
//
//    /**
//     * Share the current image using an activity chooser, so that the user can choose the
//     * app with which to share the content.
//     * This is done by saving the image to a temporary file in external storage, then specifying
//     * that file in the share intent. The name of the temporary file is kept constant, so that
//     * it's overwritten every time an image is shared from the app, so that it takes up a
//     * constant amount of space.
//     */
//    private void shareImage() {
//        if (galleryItem == null) {
//            return;
//        }
//        parentActivity.getFunnel().logGalleryShare(pageTitle, galleryItem.getUUID());
////        new ImagePipelineBitmapGetter(getActivity(), galleryItem.getThumbUrl()){
////            @Override
////            public void onSuccess(@Nullable Bitmap bitmap) {
////                if (!isAdded()) {
////                    return;
////                }
////                if (bitmap != null) {
////                    ShareUtil.shareImage(parentActivity,
////                            bitmap,
////                            new java.io.File(galleryItem.getUrl()).getName(),
////                            pageTitle.getDisplayText(),
////                            imageTitle.getCanonicalUri());
////                } else {
////                    ShareUtil.shareText(parentActivity, imageTitle);
////                }
////            }
////        }.get();
//    }
//
//    /**
//     * Checks runtime permissions first. If allowed it then proceeds with saving the image
//     * to the MediaStore.
//     */
//    private void checkPermissionsToSaveImage() {
//        if (ContextCompat.checkSelfPermission(getActivity(),
//                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
//            requestWriteStorageRuntimePermissions(WRITE_EXTERNAL_STORAGE_PERMISSION_REQUEST);
//        } else {
//            saveImage();
//        }
//    }
//
//    private void requestWriteStorageRuntimePermissions(int requestCode) {
//        requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, requestCode);
//        // once permission is granted/denied it will continue with onRequestPermissionsResult
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode,
//                                           @NonNull String[] permissions,
//                                           @NonNull int[] grantResults) {
//        switch (requestCode) {
//            case WRITE_EXTERNAL_STORAGE_PERMISSION_REQUEST:
//                if (PermissionUtil.isPermitted(grantResults)) {
//                    saveImage();
//                } else {
//                    Log.e(TAG, "Write permission was denied by user");
//                    FeedbackUtil.showMessage(getActivity(),
//                            R.string.gallery_save_image_write_permission_rationale);
//                }
//                break;
//            default:
//                throw new RuntimeException("unexpected permission request code " + requestCode);
//        }
//    }
//
//    private void saveImage() {
//        if (galleryItem == null) {
//            return;
//        }
//        parentActivity.getFunnel().logGallerySave(pageTitle, galleryItem.getUUID());
////        ((GalleryActivity) getActivity()).getDownloadReceiver().download(galleryItem);
//    }
//
//    private boolean shouldHaveWhiteBackground(String mimeType) {
//        return mimeType.contains("svg") || mimeType.contains("png") || mimeType.contains("gif");
//    }
}
