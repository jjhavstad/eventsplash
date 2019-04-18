package com.eventsplash;

import com.eventsplash.model.eventbright.events.SearchResults;
import com.eventsplash.networking.EventBriteRequestManager;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;

import io.reactivex.Flowable;
import io.reactivex.Scheduler;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.schedulers.ExecutorScheduler;
import io.reactivex.plugins.RxJavaPlugins;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

/**
 * Created by jonathanhavstad on 2/25/18.
 */

public class SearchResultAPIRequestUnitTest {
    private static final String MOCK_RESPONSE = "{\"events\": [{\"category_id\":\"103\",\"changed\":\"2019-04-17T22:38:03Z\",\"created\":\"2016-08-18T23:56:10Z\",\"currency\":\"USD\",\"description\":{\"html\":\"\\u003cP\\u003eDance at San Francisco\\u0027s premier dance-club featuring the best open-format DJs from all over.  NO COVER with RSVP for you and all your friends or reserve a VIP section.\\u003c/P\\u003e\\n\\u003cP\\u003e\\u003cSTRONG\\u003e\\u003cSTRONG\\u003eVIP TABLE RESERVATIONS TEXT: 415.766.8114 \\u003c/STRONG\\u003e\\u003cBR\\u003e\\u003c/STRONG\\u003e\\u003c/P\\u003e\\n\\u003cP\\u003e\\u003cSTRONG\\u003eExperience the all new Love + Propaganda San Francisco.\\u003c/STRONG\\u003e\\u003c/P\\u003e\\n\\u003cP\\u003e\\u003cSPAN\\u003e\\u003cSPAN\\u003eSituated in San Francisco\\u0027s Union Square district, Love and Propaganda is a crossroads where music, fashion, and art all meet to form an audio-visual experience unlike anything you\\u0027ve ever seen before. After you\\u0027ve settled into the gorgeous neo-classic inspired design, sound becomes the focal point. Expect to have your understanding of nightlife challenged, as L+P prides itself on the attention put forth to recognize the much broader community of widely acclaimed international and underground producers, DJs, and overall talent that you won’t find anywhere else.\\u003c/SPAN\\u003e\\u003c/SPAN\\u003e\\u003c/P\\u003e\\n\\u003cP\\u003e\\u003cSTRONG\\u003e85 CAMPTON PL., SAN FRANCISCO CA\\u003c/STRONG\\u003e\\u003c/P\\u003e\\n\\u003cP\\u003e\\u003cBR\\u003e\\u003c/P\\u003e\\n\\u003cP\\u003e\\u003cEM\\u003e21 \\u0026amp; Over with valid ID.\\u003c/EM\\u003e\\u003c/P\\u003e\\n\\u003cP\\u003e\\u003cBR\\u003e\\u003c/P\\u003e\\n\\u003cP\\u003e\\u003cEM\\u003eThe doorman and management reserve all rights to decline entry of any guest not in proper dress code. No sports attire or athletic gear, baseball caps, tennis shoes, baggy clothes. We respectfully encourage everyone to dress up and look stylish and put their best foot forward when attending Love + Propaganda.\\u003c/EM\\u003e\\u003c/P\\u003e\\n\\u003cP\\u003e\\u003cBR\\u003e\\u003c/P\\u003e\",\"text\":\"Dance at San Francisco\\u0027s premier dance-club featuring the best open-format DJs from all over.  NO COVER with RSVP for you and all your friends or reserve a VIP section.\\nVIP TABLE RESERVATIONS TEXT: 415.766.8114 \\nExperience the all new Love + Propaganda San Francisco.\\nSituated in San Francisco\\u0027s Union Square district, Love and Propaganda is a crossroads where music, fashion, and art all meet to form an audio-visual experience unlike anything you\\u0027ve ever seen before. After you\\u0027ve settled into the gorgeous neo-classic inspired design, sound becomes the focal point. Expect to have your understanding of nightlife challenged, as L+P prides itself on the attention put forth to recognize the much broader community of widely acclaimed international and underground producers, DJs, and overall talent that you won’t find anywhere else.\\n85 CAMPTON PL., SAN FRANCISCO CA\\n\\n21 \\u0026 Over with valid ID.\\n\\nThe doorman and management reserve all rights to decline entry of any guest not in proper dress code. No sports attire or athletic gear, baseball caps, tennis shoes, baggy clothes. We respectfully encourage everyone to dress up and look stylish and put their best foot forward when attending Love + Propaganda.\\n\"},\"end\":{\"local\":\"2019-04-21T02:00:00\",\"timezone\":\"America/Los_Angeles\",\"utc\":\"2019-04-21T09:00:00Z\"},\"format_id\":\"6\",\"hide_end_date\":false,\"hide_start_date\":false,\"id\":\"27205766179\",\"is_free\":true,\"is_locked\":false,\"is_reserved_seating\":false,\"is_series\":false,\"is_series_parent\":false,\"listed\":true,\"locale\":\"en_US\",\"logo\":{\"aspect_ratio\":\"2\",\"crop_mask\":{\"height\":1280,\"top_left\":{\"x\":0,\"y\":46},\"width\":2560},\"edge_color\":\"#486360\",\"edge_color_set\":true,\"id\":\"50302807\",\"original\":{\"height\":1707,\"url\":\"https://img.evbuc.com/https%3A%2F%2Fcdn.evbuc.com%2Fimages%2F50302807%2F174869583035%2F1%2Foriginal.jpg?auto\\u003dcompress\\u0026s\\u003d5e7918d48fe402f2d7cc28fdb4c88a9f\",\"width\":2560},\"url\":\"https://img.evbuc.com/https%3A%2F%2Fcdn.evbuc.com%2Fimages%2F50302807%2F174869583035%2F1%2Foriginal.jpg?h\\u003d200\\u0026w\\u003d450\\u0026auto\\u003dcompress\\u0026rect\\u003d0%2C46%2C2560%2C1280\\u0026s\\u003d6a01b7a62b961b14082271ae368d40fd\"},\"logo_id\":\"50302807\",\"name\":{\"html\":\"LOVE + PROPAGANDA SATURDAY\\u0026#39;S (seriesgrp)\",\"text\":\"LOVE + PROPAGANDA SATURDAY\\u0027S (seriesgrp)\"},\"online_event\":false,\"organization_id\":\"174869583035\",\"organizer_id\":\"10737892750\",\"privacy_setting\":\"unlocked\",\"resource_uri\":\"https://www.eventbriteapi.com/v3/events/27205766179/\",\"shareable\":true,\"source\":\"create_2.0\",\"start\":{\"local\":\"2019-04-20T21:30:00\",\"timezone\":\"America/Los_Angeles\",\"utc\":\"2019-04-21T04:30:00Z\"},\"status\":\"live\",\"subcategory_id\":\"3006\",\"tx_time_limit\":480,\"url\":\"https://www.eventbrite.com/e/love-propaganda-saturdays-seriesgrp-tickets-27205766179?aff\\u003debapi\",\"vanity_url\":\"https://landpsaturdays2017.eventbrite.com\",\"venue_id\":\"29277060\",\"version\":\"3.0.0\"}]}";

    private SearchResults response;

    @BeforeClass
    public static void setUpRxSchedulers() {
        Scheduler immediate = new Scheduler() {
            @Override
            public Disposable scheduleDirect(@NonNull Runnable run, long delay, @NonNull TimeUnit unit) {
                // this prevents StackOverflowErrors when scheduling with a delay
                return super.scheduleDirect(run, 0, unit);
            }

            @Override
            public Worker createWorker() {
                return new ExecutorScheduler.ExecutorWorker(Runnable::run);
            }
        };

        RxJavaPlugins.setInitIoSchedulerHandler(scheduler -> immediate);
        RxJavaPlugins.setInitComputationSchedulerHandler(scheduler -> immediate);
        RxJavaPlugins.setInitNewThreadSchedulerHandler(scheduler -> immediate);
        RxJavaPlugins.setInitSingleSchedulerHandler(scheduler -> immediate);
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(scheduler -> immediate);
    }

    @Test
    public void test_validSearchResults() throws Exception {
        MockWebServer server = new MockWebServer();
        server.enqueue(new MockResponse().setResponseCode(200).setBody(MOCK_RESPONSE));
        server.start();

        EventBriteRequestManager eventBriteRequestManager =
                new EventBriteRequestManager(server.url("/").toString(),
                        "H7F64OEURMM4B6QBKUAY",
                        "events/search/",
                        "venues/");
        SearchResults results = null;
        eventBriteRequestManager.requestSearch().subscribe(o -> {
            response = o;
        });

        server.shutdown();

        assertNotNull(response);
        assertNotNull(response.getEventList());
        assertEquals(response.getEventList().size(), 1);
    }

    @Test
    public void test_completeSearchResultsFlowable() throws Exception {
        Flowable.just(new SearchResults()).test().assertComplete();
    }
}
