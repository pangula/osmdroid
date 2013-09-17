package org.osmdroid.google.wrapper.v2;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import org.osmdroid.api.IMap;

class GoogleV1MapWrapper implements IMap {
	private final MapView mMapView;
	private MyLocationOverlay mMyLocationOverlay;

	GoogleV1MapWrapper(final MapView aMapView) {
		mMapView = aMapView;
	}

	@Override
	public void setZoom(final float aZoomLevel) {
		mMapView.getController().setZoom((int) aZoomLevel);
	}

	@Override
	public float getZoomLevel() {
		return mMapView.getZoomLevel();
	}

	@Override
	public void setCenter(final int aLatitudeE6, final int aLongitudeE6) {
		mMapView.getController().setCenter(new GeoPoint(aLatitudeE6, aLongitudeE6));
	}

	@Override
	public void setZoomAndCenter(final float aZoomLevel, final int aLatitudeE6, final int aLongitudeE6) {
		final MapController controller = mMapView.getController();
		controller.setZoom((int) aZoomLevel);
		controller.setCenter(new GeoPoint(aLatitudeE6, aLongitudeE6));
	}

	@Override
	public boolean zoomIn() {
		final MapController controller = mMapView.getController();
		return controller.zoomIn();
	}

	@Override
	public boolean zoomOut() {
		final MapController controller = mMapView.getController();
		return controller.zoomOut();
	}

	@Override
	public void setMyLocationEnabled(final boolean aEnabled) {
		if (aEnabled) {
			if (mMyLocationOverlay == null) {
				mMyLocationOverlay = new MyLocationOverlay(mMapView.getContext(), mMapView);
				mMapView.getOverlays().add(mMyLocationOverlay);
			}
			mMyLocationOverlay.enableMyLocation();
		}
		if (!aEnabled && mMyLocationOverlay != null) {
			mMyLocationOverlay.disableMyLocation();
		}
	}

	@Override
	public boolean isMyLocationEnabled() {
		return mMyLocationOverlay != null && mMyLocationOverlay.isMyLocationEnabled();
	}
}