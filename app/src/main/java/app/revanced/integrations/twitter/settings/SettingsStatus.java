package app.revanced.integrations.twitter.settings;

public class SettingsStatus {
    public static boolean changeDownloadEnabled = false;
    public static boolean customSharingDomainEnabled = false;
    public static boolean enableFontMod = false;
    public static boolean hideRecommendedUsers = false;
    public static boolean hidePromotedTrend = false;
    public static boolean hideCommunityNote = false;
    public static boolean hideFAB = false;
    public static boolean hideFABBtns = false;
    public static boolean hideViewCount = false;
    public static boolean hideInlineBmk = false;
    public static boolean hideLiveThreads = false;
    public static boolean hideBanner = false;
    public static boolean showPollResultsEnabled = false;
    public static boolean featureFlags = false;

    public static boolean hideAds = false;
    public static boolean hideGAds = false;
    public static boolean hideWTF = false;
    public static boolean hideCTS = false;
    public static boolean hideCTJ = false;
    public static boolean hideDetailedPosts = false;
    public static boolean hideRBMK = false;
    public static boolean hideRPinnedPosts = false;

    public static boolean enableReaderMode = false;
    public static boolean enableUndoPosts = false;
    public static boolean enableAppIconNNavIcon = false;

    public static boolean hideImmersivePlayer = false;

    public static boolean profileTabCustomisation = false;
    public static boolean timelineTabCustomisation = false;


    public static void enableDownloadFolder() { changeDownloadEnabled = true; }
    public static void enableCustomSharingDomain() { customSharingDomainEnabled = true; }
    public static void enableFont() { enableFontMod = true; }
    public static void enableFeatureFlags() { featureFlags = true; }

    public static void hideRecommendedUsers() { hideRecommendedUsers = true; }
    public static void hideCommunityNotes() { hideCommunityNote = true; }
    public static void hideFAB() { hideFAB = true; }
    public static void hideFABBtns() { hideFABBtns = true; }
    public static void hideViewCount() { hideViewCount = true; }
    public static void hideInlineBmk() { hideInlineBmk = true; }
    public static void hideLiveThreads() { hideLiveThreads = true; }
    public static void hideBanner() { hideBanner = true; }
    public static void enableShowPollResults() { showPollResultsEnabled = true; }

    public static void hideAds() { hideAds = true; }
    public static void hideGAds() { hideGAds = true; }
    public static void hideWhoToFollow() { hideWTF = true; }
    public static void hideCreatorsToSub() { hideCTS = true; }
    public static void hideCommToJoin() { hideCTJ = true; }
    public static void hideDetailedPost() { hideDetailedPosts = true; }
    public static void hideRevistBookmark() { hideRBMK = true; }
    public static void hideRevistPinnedPost() { hideRPinnedPosts = true; }
    public static void hidePromotedTrends() { hidePromotedTrend = true; }

    public static void enableReaderMode() { enableReaderMode = true; }
    public static void enableUndoPosts() { enableUndoPosts = true; }
    public static void enableAppIconNNavIcon() { enableAppIconNNavIcon = true; }
    public static void hideImmersivePlayer() { hideImmersivePlayer = true; }

    public static void profileTabCustomisation() { profileTabCustomisation = true; }
    public static void timelineTabCustomisation() { timelineTabCustomisation = true; }

    public static boolean enableTimelineSection(){ return (hideLiveThreads || hideBanner || hideInlineBmk || showPollResultsEnabled || hideImmersivePlayer); }
    public static boolean enableMiscSection() { return (enableFontMod || hideRecommendedUsers || hideFAB || hideViewCount || hideCommunityNote || customSharingDomainEnabled || hideFABBtns); }
    public static boolean enableAdsSection() {return (hideAds|| hideGAds || hideWTF || hideCTS || hideCTJ || hideDetailedPosts || hideRBMK ||hidePromotedTrend); }

    public static boolean enablePremiumSection() {return (enableReaderMode || enableUndoPosts || enableAppIconNNavIcon); }
    public static boolean enableCustomisationSection() {return (profileTabCustomisation || timelineTabCustomisation); }

    public static void load() {}
}
