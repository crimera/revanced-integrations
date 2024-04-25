package app.revanced.integrations.twitter.patches.links;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.util.Objects;
import java.util.Set;

public final class OpenLinksWithAppChooserPatch {
    public static void openWithChooser(final Context context, final Intent originalIntent, final Bundle bundle) {
        Set<String> categories = originalIntent.getCategories();

        // original credit: TwiFucker, updated for newer build
        if (originalIntent.getAction() == null) {
            context.startActivity(originalIntent, bundle);
            return;
        }

        final Intent intent = new Intent("android.intent.action.VIEW", originalIntent.getData());
        context.startActivity(Intent.createChooser(intent, null));
    }
}
