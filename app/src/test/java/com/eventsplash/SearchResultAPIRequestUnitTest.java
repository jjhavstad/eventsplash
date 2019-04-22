package com.eventsplash;

import com.eventsplash.eventdetail.models.EventWithVenue;
import com.eventsplash.model.eventbright.events.Event;
import com.eventsplash.model.eventbright.events.SearchResults;
import com.eventsplash.model.eventbright.venues.Venue;
import com.eventsplash.networking.EventBriteRequestManager;
import com.google.gson.Gson;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;

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
    private SearchResults response;
    private Venue venueResponse;
    private List<EventWithVenue> eventWithVenueList;
    private EventBriteRequestManager eventBriteRequestManager;

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
    public void test_validSearchAllResults() throws Exception {
        String mockSearchResponse = "{\"events\": [{\"category_id\":\"103\",\"changed\":\"2019-04-17T22:38:03Z\",\"created\":\"2016-08-18T23:56:10Z\",\"currency\":\"USD\",\"description\":{\"html\":\"\\u003cP\\u003eDance at San Francisco\\u0027s premier dance-club featuring the best open-format DJs from all over.  NO COVER with RSVP for you and all your friends or reserve a VIP section.\\u003c/P\\u003e\\n\\u003cP\\u003e\\u003cSTRONG\\u003e\\u003cSTRONG\\u003eVIP TABLE RESERVATIONS TEXT: 415.766.8114 \\u003c/STRONG\\u003e\\u003cBR\\u003e\\u003c/STRONG\\u003e\\u003c/P\\u003e\\n\\u003cP\\u003e\\u003cSTRONG\\u003eExperience the all new Love + Propaganda San Francisco.\\u003c/STRONG\\u003e\\u003c/P\\u003e\\n\\u003cP\\u003e\\u003cSPAN\\u003e\\u003cSPAN\\u003eSituated in San Francisco\\u0027s Union Square district, Love and Propaganda is a crossroads where music, fashion, and art all meet to form an audio-visual experience unlike anything you\\u0027ve ever seen before. After you\\u0027ve settled into the gorgeous neo-classic inspired design, sound becomes the focal point. Expect to have your understanding of nightlife challenged, as L+P prides itself on the attention put forth to recognize the much broader community of widely acclaimed international and underground producers, DJs, and overall talent that you won’t find anywhere else.\\u003c/SPAN\\u003e\\u003c/SPAN\\u003e\\u003c/P\\u003e\\n\\u003cP\\u003e\\u003cSTRONG\\u003e85 CAMPTON PL., SAN FRANCISCO CA\\u003c/STRONG\\u003e\\u003c/P\\u003e\\n\\u003cP\\u003e\\u003cBR\\u003e\\u003c/P\\u003e\\n\\u003cP\\u003e\\u003cEM\\u003e21 \\u0026amp; Over with valid ID.\\u003c/EM\\u003e\\u003c/P\\u003e\\n\\u003cP\\u003e\\u003cBR\\u003e\\u003c/P\\u003e\\n\\u003cP\\u003e\\u003cEM\\u003eThe doorman and management reserve all rights to decline entry of any guest not in proper dress code. No sports attire or athletic gear, baseball caps, tennis shoes, baggy clothes. We respectfully encourage everyone to dress up and look stylish and put their best foot forward when attending Love + Propaganda.\\u003c/EM\\u003e\\u003c/P\\u003e\\n\\u003cP\\u003e\\u003cBR\\u003e\\u003c/P\\u003e\",\"text\":\"Dance at San Francisco\\u0027s premier dance-club featuring the best open-format DJs from all over.  NO COVER with RSVP for you and all your friends or reserve a VIP section.\\nVIP TABLE RESERVATIONS TEXT: 415.766.8114 \\nExperience the all new Love + Propaganda San Francisco.\\nSituated in San Francisco\\u0027s Union Square district, Love and Propaganda is a crossroads where music, fashion, and art all meet to form an audio-visual experience unlike anything you\\u0027ve ever seen before. After you\\u0027ve settled into the gorgeous neo-classic inspired design, sound becomes the focal point. Expect to have your understanding of nightlife challenged, as L+P prides itself on the attention put forth to recognize the much broader community of widely acclaimed international and underground producers, DJs, and overall talent that you won’t find anywhere else.\\n85 CAMPTON PL., SAN FRANCISCO CA\\n\\n21 \\u0026 Over with valid ID.\\n\\nThe doorman and management reserve all rights to decline entry of any guest not in proper dress code. No sports attire or athletic gear, baseball caps, tennis shoes, baggy clothes. We respectfully encourage everyone to dress up and look stylish and put their best foot forward when attending Love + Propaganda.\\n\"},\"end\":{\"local\":\"2019-04-21T02:00:00\",\"timezone\":\"America/Los_Angeles\",\"utc\":\"2019-04-21T09:00:00Z\"},\"format_id\":\"6\",\"hide_end_date\":false,\"hide_start_date\":false,\"id\":\"27205766179\",\"is_free\":true,\"is_locked\":false,\"is_reserved_seating\":false,\"is_series\":false,\"is_series_parent\":false,\"listed\":true,\"locale\":\"en_US\",\"logo\":{\"aspect_ratio\":\"2\",\"crop_mask\":{\"height\":1280,\"top_left\":{\"x\":0,\"y\":46},\"width\":2560},\"edge_color\":\"#486360\",\"edge_color_set\":true,\"id\":\"50302807\",\"original\":{\"height\":1707,\"url\":\"https://img.evbuc.com/https%3A%2F%2Fcdn.evbuc.com%2Fimages%2F50302807%2F174869583035%2F1%2Foriginal.jpg?auto\\u003dcompress\\u0026s\\u003d5e7918d48fe402f2d7cc28fdb4c88a9f\",\"width\":2560},\"url\":\"https://img.evbuc.com/https%3A%2F%2Fcdn.evbuc.com%2Fimages%2F50302807%2F174869583035%2F1%2Foriginal.jpg?h\\u003d200\\u0026w\\u003d450\\u0026auto\\u003dcompress\\u0026rect\\u003d0%2C46%2C2560%2C1280\\u0026s\\u003d6a01b7a62b961b14082271ae368d40fd\"},\"logo_id\":\"50302807\",\"name\":{\"html\":\"LOVE + PROPAGANDA SATURDAY\\u0026#39;S (seriesgrp)\",\"text\":\"LOVE + PROPAGANDA SATURDAY\\u0027S (seriesgrp)\"},\"online_event\":false,\"organization_id\":\"174869583035\",\"organizer_id\":\"10737892750\",\"privacy_setting\":\"unlocked\",\"resource_uri\":\"https://www.eventbriteapi.com/v3/events/27205766179/\",\"shareable\":true,\"source\":\"create_2.0\",\"start\":{\"local\":\"2019-04-20T21:30:00\",\"timezone\":\"America/Los_Angeles\",\"utc\":\"2019-04-21T04:30:00Z\"},\"status\":\"live\",\"subcategory_id\":\"3006\",\"tx_time_limit\":480,\"url\":\"https://www.eventbrite.com/e/love-propaganda-saturdays-seriesgrp-tickets-27205766179?aff\\u003debapi\",\"vanity_url\":\"https://landpsaturdays2017.eventbrite.com\",\"venue_id\":\"29277060\",\"version\":\"3.0.0\"}]}";
        Gson gson = new Gson();
        SearchResults searchResults = gson.fromJson(mockSearchResponse, SearchResults.class);
        Event event = searchResults.getEventList().get(0);

        MockWebServer server = new MockWebServer();
        server.enqueue(new MockResponse().setResponseCode(200).setBody(mockSearchResponse));
        server.start();

        EventBriteRequestManager eventBriteRequestManager =
                new EventBriteRequestManager(server.url("/").toString(),
                        "H7F64OEURMM4B6QBKUAY",
                        "events/search/",
                        "venues/");

        eventBriteRequestManager.requestSearch().subscribe(o -> {
            response = o;
        });

        server.shutdown();

        assertNotNull(response);
        assertNotNull(response.getEventList());
        assertEquals(response.getEventList().size(), 1);
        assertEquals(response.getEventList().get(0), event);
    }

    @Test
    public void test_validSearchByLatLonResults() throws Exception {
        String eventRepsoneJson = "{\"category_id\":\"113\",\"changed\":\"2019-04-21T22:44:51Z\",\"created\":\"2019-01-15T21:56:08Z\",\"currency\":\"USD\",\"description\":{\"html\":\"\\u003cP\\u003eDiscover the world from San Jose. International Children’s Festival unites world cultures in the Bay Area through dance, carnival rides, incredible kids/adults activities,and multicultural deliciouns food. Join us and explore a variety of cultures from all arouond the world represented in your neighborhood!\\u003c/P\\u003e\\r\\n\\u003cP\\u003e\\u003cSTRONG\\u003eDear All, we have been receiving a lot of requests for free entrance to the festival. Upon lengthy discussions, the International Children\\u0027s Festival Committee decided to waive the entrance fee as in the past years. For all those who already paid the fee, they will get $2 activity tickets at the site of the festival instead. Thank you for your interest and see you at Discovery Meadow on April 27th, Saturday.\\u003c/STRONG\\u003e\\u003c/P\\u003e\\r\\n\\u003cP\\u003e\\u003cOBJECT WIDTH\\u003d\\\"425\\\" HEIGHT\\u003d\\\"350\\\" DATA\\u003d\\\"https://www.youtube.com/v/3hx82rXwyj4\\\" TYPE\\u003d\\\"application/x-shockwave-flash\\\"\\u003e\\u003cPARAM NAME\\u003d\\\"src\\\" VALUE\\u003d\\\"https://www.youtube.com/v/3hx82rXwyj4\\\"\\u003e\\u003c/PARAM\\u003e\\u003c/OBJECT\\u003e \\u003cBR\\u003e\\u003c/P\\u003e\\r\\n\\u003cP\\u003eFREE ADMISSION FOR KIDS AGE 2 \\u0026amp; UNDER.\\u003c/P\\u003e\\r\\n\\u003cP\\u003e\\u003cSPAN STYLE\\u003d\\\"font-size: small;\\\"\\u003e\\u003cSPAN STYLE\\u003d\\\"font-family: \\u0027Helvetica Neue\\u0027, Helvetica, Arial, sans-serif; color: #008000;\\\"\\u003e\\u003cSTRONG\\u003e1-day family friendly refund policy:\\u003c/STRONG\\u003e We understand sometimes unexpected things happen, especially when you have a family with small kids, that\\u0027s why we offer a very flexible 1-day refund policy. Tickets are fully refundable if requested by \\u003cSTRONG\\u003e10AM of April 26th, 2019.\\u003c/STRONG\\u003e\\u003c/SPAN\\u003e\\u003cSPAN STYLE\\u003d\\\"font-family: \\u0027Helvetica Neue\\u0027, Helvetica, Arial, sans-serif;\\\"\\u003e \\u003c/SPAN\\u003e\\u003c/SPAN\\u003e\\u003c/P\\u003e\\r\\n\\u003cP STYLE\\u003d\\\"font-family: Verdana, Arial, Helvetica, sans-serif; font-size: 10px;\\\"\\u003e\\u003cSPAN STYLE\\u003d\\\"font-size: small;\\\"\\u003eDid you get your activity tickets* yet? To enjoy all we have to offer, purchase your activity tickets now at a discount! Skip the lines, pick-up your activity tickets from will-call booths and go straight into the fun. \\u003c/SPAN\\u003e\\u003c/P\\u003e\\r\\n\\u003cP STYLE\\u003d\\\"font-family: Verdana, Arial, Helvetica, sans-serif; font-size: 10px;\\\"\\u003e\\u003cA HREF\\u003d\\\"https://kidzfest-rides-2019.eventbrite.com\\\"\\u003e\\u003cSPAN STYLE\\u003d\\\"font-size: small;\\\"\\u003ehttps://kidzfest-rides-2019.eventbrite.com\\u003c/SPAN\\u003e\\u003c/A\\u003e\\u003c/P\\u003e\\r\\n\\u003cP STYLE\\u003d\\\"font-family: Verdana, Arial, Helvetica, sans-serif; font-size: 10px;\\\"\\u003e\\u003cSPAN STYLE\\u003d\\\"font-size: small;\\\"\\u003e*All participants require tickets for the following activities (pricing varies, on average each activity will cost 3-5 tickets): Awesome Jumpers, Lovely Petting Zoo, Face Painting, Balloon Artist, Cotton Candy, Slime Booth, and  all-time favorite Carnival Rides: The Tubs of Fun, The Jeep Ride, The Mind Winder plus many exciting activities in kidsland. And the 10TH YEAR SURPRISE RIDE (to be announced on April 10th).\\u003c/SPAN\\u003e\\u003c/P\\u003e\",\"text\":\"Discover the world from San Jose. International Children’s Festival unites world cultures in the Bay Area through dance, carnival rides, incredible kids/adults activities,and multicultural deliciouns food. Join us and explore a variety of cultures from all arouond the world represented in your neighborhood!\\r\\nDear All, we have been receiving a lot of requests for free entrance to the festival. Upon lengthy discussions, the International Children\\u0027s Festival Committee decided to waive the entrance fee as in the past years. For all those who already paid the fee, they will get $2 activity tickets at the site of the festival instead. Thank you for your interest and see you at Discovery Meadow on April 27th, Saturday.\\r\\n \\r\\nFREE ADMISSION FOR KIDS AGE 2 \\u0026 UNDER.\\r\\n1-day family friendly refund policy: We understand sometimes unexpected things happen, especially when you have a family with small kids, that\\u0027s why we offer a very flexible 1-day refund policy. Tickets are fully refundable if requested by 10AM of April 26th, 2019. \\r\\nDid you get your activity tickets* yet? To enjoy all we have to offer, purchase your activity tickets now at a discount! Skip the lines, pick-up your activity tickets from will-call booths and go straight into the fun. \\r\\nhttps://kidzfest-rides-2019.eventbrite.com\\r\\n*All participants require tickets for the following activities (pricing varies, on average each activity will cost 3-5 tickets): Awesome Jumpers, Lovely Petting Zoo, Face Painting, Balloon Artist, Cotton Candy, Slime Booth, and  all-time favorite Carnival Rides: The Tubs of Fun, The Jeep Ride, The Mind Winder plus many exciting activities in kidsland. And the 10TH YEAR SURPRISE RIDE (to be announced on April 10th).\"},\"end\":{\"local\":\"2019-04-27T17:00:00\",\"timezone\":\"America/Los_Angeles\",\"utc\":\"2019-04-28T00:00:00Z\"},\"format_id\":\"5\",\"hide_end_date\":false,\"hide_start_date\":false,\"id\":\"54946883608\",\"is_free\":false,\"is_locked\":false,\"is_reserved_seating\":false,\"is_series\":false,\"is_series_parent\":false,\"listed\":true,\"locale\":\"en_US\",\"logo\":{\"aspect_ratio\":\"2\",\"crop_mask\":{\"height\":1116,\"top_left\":{\"x\":0,\"y\":1051},\"width\":2232},\"edge_color\":\"#f9fdfe\",\"edge_color_set\":true,\"id\":\"55628473\",\"original\":{\"height\":3033,\"url\":\"https://img.evbuc.com/https%3A%2F%2Fcdn.evbuc.com%2Fimages%2F55628473%2F186462043174%2F1%2Foriginal.20190124-005111?auto\\u003dcompress\\u0026s\\u003d3a2d31013f1312d0110d85dce65ba7f7\",\"width\":2233},\"url\":\"https://img.evbuc.com/https%3A%2F%2Fcdn.evbuc.com%2Fimages%2F55628473%2F186462043174%2F1%2Foriginal.20190124-005111?h\\u003d200\\u0026w\\u003d450\\u0026auto\\u003dcompress\\u0026rect\\u003d0%2C1051%2C2232%2C1116\\u0026s\\u003d6e93f1049ecc508e38bb757928d86374\"},\"logo_id\":\"55628473\",\"name\":{\"html\":\"10th Int\\u0026#39;l Children\\u0026#39;s Festival in the Bay Area-Dance,Food,Rides,Arts,Culture\",\"text\":\"10th Int\\u0027l Children\\u0027s Festival in the Bay Area-Dance,Food,Rides,Arts,Culture\"},\"online_event\":false,\"organization_id\":\"186462043174\",\"organizer_id\":\"15971809488\",\"privacy_setting\":\"unlocked\",\"resource_uri\":\"https://www.eventbriteapi.com/v3/events/54946883608/\",\"shareable\":true,\"source\":\"create_2.0\",\"start\":{\"local\":\"2019-04-27T10:00:00\",\"timezone\":\"America/Los_Angeles\",\"utc\":\"2019-04-27T17:00:00Z\"},\"status\":\"live\",\"subcategory_id\":\"13007\",\"tx_time_limit\":480,\"url\":\"https://www.eventbrite.com/e/10th-intl-childrens-festival-in-the-bay-area-dancefoodridesartsculture-tickets-54946883608?aff\\u003debapi\",\"vanity_url\":\"https://kidzfest-2019.eventbrite.com\",\"venue_id\":\"31381054\",\"version\":\"3.0.0\"}";
        Gson gson = new Gson();
        Event eventResponse = gson.fromJson(eventRepsoneJson, Event.class);
        List<Event> eventList = new ArrayList();
        eventList.add(eventResponse);
        SearchResults mockSearchResponse = new SearchResults();
        mockSearchResponse.setEventList(eventList);

        MockWebServer server = new MockWebServer();
        server.enqueue(new MockResponse().setResponseCode(200).setBody(gson.toJson(mockSearchResponse)));
        server.start();

        EventBriteRequestManager eventBriteRequestManager =
                new EventBriteRequestManager(server.url("/").toString(),
                        "H7F64OEURMM4B6QBKUAY",
                        "events/search/",
                        "venues/");

        double latitude = 37.4219983;
        double longitude = -122.084;
        int radius = 50;
        eventBriteRequestManager.requestSearchByLatLon(latitude, longitude, radius).subscribe(o -> {
            response = o;
        });

        server.shutdown();

        assertNotNull(response);
        assertNotNull(response.getEventList());
        assertEquals(response.getEventList().size(), 1);
        assertEquals(response.getEventList().get(0), eventResponse);
    }

    @Test
    public void test_validSearchByVenueResults() throws Exception {
        String mockVenueResponse = "{\"address\":{\"address_1\":\"85 Campton Place\",\"address_2\":null,\"city\":\"San Francisco\",\"region\":\"CA\",\"postal_code\":\"94108\",\"country\":\"US\",\"latitude\":\"37.7887903\",\"longitude\":\"-122.4065981\",\"localized_address_display\":\"85 Campton Place, San Francisco, CA 94108\",\"localized_area_display\":\"San Francisco, CA\",\"localized_multi_line_address_display\":[\"85 Campton Place\",\"San Francisco, CA 94108\"]},\"resource_uri\":\"https://www.eventbriteapi.com/v3/venues/29277060/\",\"id\":\"29277060\",\"age_restriction\":null,\"capacity\":null,\"name\":\"Love + Propaganda\",\"latitude\":\"37.7887903\",\"longitude\":\"-122.4065981\"}";
        Gson gson = new Gson();
        Venue venue = gson.fromJson(mockVenueResponse, Venue.class);

        MockWebServer server = new MockWebServer();
        server.enqueue(new MockResponse().setResponseCode(200).setBody(mockVenueResponse));
        server.start();

        EventBriteRequestManager eventBriteRequestManager =
                new EventBriteRequestManager(server.url("/").toString(),
                        "H7F64OEURMM4B6QBKUAY",
                        "events/search/",
                        "venues/");

        String venueId = "29277060";
        eventBriteRequestManager.requestVenueInformation(venueId).subscribe(o -> {
            venueResponse = o;
        });

        server.shutdown();

        assertNotNull(venueResponse);
        assertEquals(venueResponse, venue);
    }

    @Test
    public void test_validSearchByEventVenueListResults() throws Exception {

        String eventJson = "{\"category_id\":\"101\",\"changed\":\"2019-04-19T19:11:30Z\",\"created\":\"2015-09-21T17:44:23Z\",\"currency\":\"USD\",\"description\":{\"html\":\"\\u003cP\\u003e\\u003cSTRONG\\u003e\\u003cSPAN\\u003eThe 20th Annual\\u003c/SPAN\\u003e \\u003c/STRONG\\u003e\\u003cBR\\u003e\\u003c/P\\u003e\\n\\u003cP\\u003e\\u003cSTRONG\\u003e\\u003cSPAN\\u003eSan Jose Career Fair - Sales \\u0026amp; Professional Job Fair \\u0026amp; Career Fair\\u003c/SPAN\\u003e\\u003c/STRONG\\u003e\\u003c/P\\u003e\\n\\u003cP\\u003e\\u003cSPAN\\u003e\\u003cSTRONG\\u003e\\u003cSPAN\\u003eThursday - May 23rd, 2019 *\\u003cSPAN\\u003e11:30 AM to 1:30 PM\\u003c/SPAN\\u003e\\u003c/SPAN\\u003e\\u003c/STRONG\\u003e\\u003cSPAN\\u003e\\u003cBR\\u003e\\u003c/SPAN\\u003e\\u003c/SPAN\\u003e\\u003c/P\\u003e\\n\\u003cP\\u003e \\u003c/P\\u003e\\n\\u003cP\\u003e\\u003cSPAN\\u003e\\u003cSPAN\\u003eMeet, sit down and interview with Nationally Known employers at The San Jose Career Fair - Sales \\u0026amp; Professional Job Fair.  Professional Dress (suit \\u0026amp; tie or business suit) Bring plenty of resumes.\\u003c/SPAN\\u003e\\u003cSTRONG\\u003e \\u003cBR\\u003e\\u003c/STRONG\\u003e\\u003c/SPAN\\u003e\\u003c/P\\u003e\\n\\u003cP\\u003e \\u003c/P\\u003e\\n\\u003cP\\u003e\\u003cSPAN\\u003e\\u003cSTRONG\\u003eSome of the industries represented at The San Jose Career Fair and Job Fair: \\u003c/STRONG\\u003e\\u003c/SPAN\\u003e\\u003c/P\\u003e\\n\\u003cP\\u003e\\u003cSPAN\\u003e\\u003cSTRONG\\u003e\\u003c/STRONG\\u003e\\u003cSTRONG\\u003e\\u003c/STRONG\\u003e Inside Sales, Outside Sales  - Tech Sales - Retail - Marketing Sales - Customer Service, Cashiers - Admin - Financial -  B2B Sales - Customer Service - Sales Management - Industrial - Consulting - Customer Service - Security Sales - Recruiting - Military Enlisted Veterans - Insurance -  Retail - Entry Level Management - Marketing Sales - Medical Device -  and more\\u003c/SPAN\\u003e\\u003c/P\\u003e\\n\\u003cP\\u003e \\u003c/P\\u003e\\n\\u003cP\\u003e\\u003cSTRONG\\u003e_________________________________________________________________________\\u003c/STRONG\\u003e \\u003cBR\\u003e\\u003c/P\\u003e\\n\\u003cP\\u003e\\u003cSTRONG\\u003eThe 20th Annual San Jose Career Fair - Sales \\u0026amp; Professional Job Fair \\u003c/STRONG\\u003e\\u003cBR\\u003e\\u003cSPAN\\u003e\\u003cSTRONG\\u003e\\u003cSPAN\\u003e\\u003cSTRONG\\u003eThursday, May 23, 2019\\u003c/STRONG\\u003e\\u003c/SPAN\\u003e\\u003c/STRONG\\u003e\\u003c/SPAN\\u003e\\u003cBR\\u003e\\u003cSTRONG\\u003e11:30 AM to 1:30 PM\\u003c/STRONG\\u003e\\u003cBR\\u003e\\u003cSTRONG\\u003eEmbassy Suites-Silicon Valley\\u003c/STRONG\\u003e\\u003cBR\\u003e2885 Lakeside Dr.\\u003cBR\\u003eSanta Clara, Ca 95054\\u003c/P\\u003e\\n\\u003cP\\u003e \\u003cSPAN\\u003e\\u003cSTRONG\\u003e\\u003cSPAN\\u003e\\u003cSTRONG\\u003e\\u003cSPAN\\u003e*Come with your Eventbrite ticket for 1st admission into the San Jose Career Fair \\u0026amp; Hiring Event`\\u003c/SPAN\\u003e\\u003c/STRONG\\u003e\\u003c/SPAN\\u003e\\u003c/STRONG\\u003e\\u003c/SPAN\\u003e\\u003c/P\\u003e\\n\\u003cP\\u003e\\u003cBR\\u003e\\u003c/P\\u003e\\n\\u003cP\\u003e\\u003cSTRONG\\u003e** EMPLOYERS:\\u003c/STRONG\\u003e\\u003cSPAN\\u003e Please Contact us for \\u003c/SPAN\\u003e\\u003cSTRONG\\u003e\\u003cA HREF\\u003d\\\"https://www.diversitycareergroup.com/employer-career-fair-information/\\\" TARGET\\u003d\\\"_blank\\\" REL\\u003d\\\"nofollow noopener noreferrer\\\"\\u003eBest Rates / DCG Career Fairs\\u003c/A\\u003e\\u003c/STRONG\\u003e\\u003c/P\\u003e\\n\\u003cP\\u003e  \\u003cBR\\u003e\\u003c/P\\u003e\\n\\u003cP\\u003e\\u003cSPAN\\u003e\\u003cSPAN\\u003e\\u003cSTRONG\\u003eBenefit packages vary by company at The San Jose Career Fair and Sales \\u0026amp; Professional Job Fair, most include a combination of the following :\\u003c/STRONG\\u003e\\u003c/SPAN\\u003e\\u003c/SPAN\\u003e \\u003cBR\\u003e\\u003c/P\\u003e\\n\\u003cP\\u003e\\u003cSPAN\\u003e\\u003cSPAN\\u003e\\u003cSPAN\\u003e\\u003cSPAN\\u003e\\u003cSPAN\\u003eCorporate Benefits Packages: \\u003c/SPAN\\u003e\\u003cBR\\u003eSalaried Positions\\u003cBR\\u003eBase Salary + Positions \\u003cBR\\u003eBonuses\\u003cBR\\u003eCommission\\u003cBR\\u003eCar or Car Allowance\\u003cBR\\u003eCell/Laptop\\u003cBR\\u003eFull Medical/Dental/Vision\\u003cBR\\u003eLife Insurance\\u003cBR\\u003ePaid Holidays\\u003cBR\\u003eExpense Account\\u003cBR\\u003eComplete Corporate Training\\u003cBR\\u003eCollege Tuition Reimbursement\\u003cBR\\u003eManagement Training\\u003cBR\\u003eRapid Career Advancement\\u003c/SPAN\\u003e\\u003c/SPAN\\u003e\\u003c/SPAN\\u003e\\u003c/SPAN\\u003e\\u003c/P\\u003e\\n\\u003cP\\u003e\\u003cBR\\u003e\\u003c/P\\u003e\\n\\u003cP\\u003e\\u003cSTRONG\\u003e\\u003cSPAN\\u003e\\u003cSPAN\\u003e*Free Attendance and Parking to All Candidates attending the San Jose Career Fair and Hiring Event - Open to all candidates!\\u003c/SPAN\\u003e \\u003c/SPAN\\u003e\\u003c/STRONG\\u003e \\u003cBR\\u003e\\u003c/P\\u003e\\n\\u003cP\\u003e\\u003cSPAN\\u003e \\u003cEM\\u003e\\u003cSPAN\\u003e\\u003cSPAN\\u003e\\u003cSPAN ITEMPROP\\u003d\\\"description\\\"\\u003e\\u003cSPAN\\u003e\\u003cSTRONG\\u003e**\\u003c/STRONG\\u003eBe p\\u003c/SPAN\\u003e\\u003cSPAN\\u003erepared to interview with hiring managers and recruiters at the San Jose Career Fair, \\u003c/SPAN\\u003e\\u003c/SPAN\\u003eProfessional Dress (suit \\u0026amp; tie or business suit) required. Bring plenty of resumes.\\u003c/SPAN\\u003e\\u003c/SPAN\\u003e\\u003c/EM\\u003e\\u003c/SPAN\\u003e\\u003c/P\\u003e\\n\\u003cP\\u003e \\u003c/P\\u003e\\n\\u003cP\\u003e\\u003cSPAN\\u003e\\u003cSPAN\\u003e\\u003cSPAN\\u003e\\u003cSTRONG\\u003eEmployers:\\u003c/STRONG\\u003e \\u003c/SPAN\\u003e\\u003cSTRONG\\u003ePlease contact us for Special Discounts to participate in The San Jose Career Fair - Sales \\u0026amp; Professional Hiring Event.\\u003c/STRONG\\u003e\\u003c/SPAN\\u003e\\u003c/SPAN\\u003e\\u003c/P\\u003e\\n\\u003cP\\u003e \\u003c/P\\u003e\\n\\u003cP\\u003e\\u003cSTRONG\\u003e\\u003cA HREF\\u003d\\\"https://www.diversitycareergroup.com/\\\" TARGET\\u003d\\\"_blank\\\" TITLE\\u003d\\\"www.DiversityCareerGroup.com\\\" REL\\u003d\\\"nofollow noopener noreferrer\\\"\\u003ewww.diversitycareergroup.com/am-venues/san-jose-silicon-valley-career-fairs/\\u003c/A\\u003e\\u003c/STRONG\\u003e\\u003c/P\\u003e\\n\\u003cP\\u003eor\\u003c/P\\u003e\\n\\u003cP\\u003e\\u003cSTRONG\\u003e\\u003cA HREF\\u003d\\\"https://www.diversitycareergroup.com/employers/\\\" TARGET\\u003d\\\"_blank\\\" REL\\u003d\\\"nofollow noopener noreferrer\\\"\\u003eRequest Employer Pricing Information\\u003c/A\\u003e\\u003c/STRONG\\u003e\\u003c/P\\u003e\\n\\u003cP\\u003eINDUSTRIES THAT HIRE AT OUR CAREER FAIRS\\u003cBR\\u003eAccommodations, Accounting, Advertising, Aerospace, Agriculture \\u0026amp; Agribusiness, Air Transportation, Apparel \\u0026amp; Accessories, Auto, Banking, Beauty \\u0026amp; Cosmetics, Biotechnology, Chemical, Communications, Computer, Construction, Consulting, Consumer Products, Education, Electronics, Employment, Energy, Entertainment \\u0026amp; Recreation, Fashion, Financial Services, Fine Arts, Food \\u0026amp; Beverage, Green Technology, Health, Information, Information Technology, Insurance, Journalism \\u0026amp; News, Legal Services, Manufacturing, Media \\u0026amp; Broadcasting, Medical Devices \\u0026amp; Supplies, Motion Pictures \\u0026amp; Video, Music, Pharmaceutical, Public Administration, Public Relations, Publishing, Real Estate, Retail, Service, Sports, Technology, Telecommunications, Tourism, Transportation, Travel, Utilities, Video Game, Web Services\\u003c/P\\u003e\\n\\u003cP\\u003eKey Words: Account Executive, Sales Representative, Account Manager, B2B, retail sales, furniture, sales, financial services, customer service, Business Development Manager, Sales Manager, Financial Services, Insurance, Telecommunications, Pharmaceutical, Hospital Representative, Medical, Marketing, Advertising, Healthcare, office equipment, Telecom, Information Technology, Software, Security Services, Advertising, Payroll, Sales Management, Sales Trainee, Management Trainee, At home sales, cold calling, telemarketer, telemarketing, financial advisor, insurance sales, outside sales, outside sales representative, sales executive, salesman, salesperson, sales women, marketing\\u003c/P\\u003e\",\"text\":\"The 20th Annual \\nSan Jose Career Fair - Sales \\u0026 Professional Job Fair \\u0026 Career Fair\\nThursday - May 23rd, 2019 *11:30 AM to 1:30 PM\\n \\nMeet, sit down and interview with Nationally Known employers at The San Jose Career Fair - Sales \\u0026 Professional Job Fair.  Professional Dress (suit \\u0026 tie or business suit) Bring plenty of resumes. \\n \\nSome of the industries represented at The San Jose Career Fair and Job Fair: \\n Inside Sales, Outside Sales  - Tech Sales - Retail - Marketing Sales - Customer Service, Cashiers - Admin - Financial -  B2B Sales - Customer Service - Sales Management - Industrial - Consulting - Customer Service - Security Sales - Recruiting - Military Enlisted Veterans - Insurance -  Retail - Entry Level Management - Marketing Sales - Medical Device -  and more\\n \\n_________________________________________________________________________ \\nThe 20th Annual San Jose Career Fair - Sales \\u0026 Professional Job Fair Thursday, May 23, 201911:30 AM to 1:30 PMEmbassy Suites-Silicon Valley2885 Lakeside Dr.Santa Clara, Ca 95054\\n *Come with your Eventbrite ticket for 1st admission into the San Jose Career Fair \\u0026 Hiring Event`\\n\\n** EMPLOYERS: Please Contact us for Best Rates / DCG Career Fairs\\n  \\nBenefit packages vary by company at The San Jose Career Fair and Sales \\u0026 Professional Job Fair, most include a combination of the following : \\nCorporate Benefits Packages: Salaried PositionsBase Salary + Positions BonusesCommissionCar or Car AllowanceCell/LaptopFull Medical/Dental/VisionLife InsurancePaid HolidaysExpense AccountComplete Corporate TrainingCollege Tuition ReimbursementManagement TrainingRapid Career Advancement\\n\\n*Free Attendance and Parking to All Candidates attending the San Jose Career Fair and Hiring Event - Open to all candidates!  \\n **Be prepared to interview with hiring managers and recruiters at the San Jose Career Fair, Professional Dress (suit \\u0026 tie or business suit) required. Bring plenty of resumes.\\n \\nEmployers: Please contact us for Special Discounts to participate in The San Jose Career Fair - Sales \\u0026 Professional Hiring Event.\\n \\nwww.diversitycareergroup.com/am-venues/san-jose-silicon-valley-career-fairs/\\nor\\nRequest Employer Pricing Information\\nINDUSTRIES THAT HIRE AT OUR CAREER FAIRSAccommodations, Accounting, Advertising, Aerospace, Agriculture \\u0026 Agribusiness, Air Transportation, Apparel \\u0026 Accessories, Auto, Banking, Beauty \\u0026 Cosmetics, Biotechnology, Chemical, Communications, Computer, Construction, Consulting, Consumer Products, Education, Electronics, Employment, Energy, Entertainment \\u0026 Recreation, Fashion, Financial Services, Fine Arts, Food \\u0026 Beverage, Green Technology, Health, Information, Information Technology, Insurance, Journalism \\u0026 News, Legal Services, Manufacturing, Media \\u0026 Broadcasting, Medical Devices \\u0026 Supplies, Motion Pictures \\u0026 Video, Music, Pharmaceutical, Public Administration, Public Relations, Publishing, Real Estate, Retail, Service, Sports, Technology, Telecommunications, Tourism, Transportation, Travel, Utilities, Video Game, Web Services\\nKey Words: Account Executive, Sales Representative, Account Manager, B2B, retail sales, furniture, sales, financial services, customer service, Business Development Manager, Sales Manager, Financial Services, Insurance, Telecommunications, Pharmaceutical, Hospital Representative, Medical, Marketing, Advertising, Healthcare, office equipment, Telecom, Information Technology, Software, Security Services, Advertising, Payroll, Sales Management, Sales Trainee, Management Trainee, At home sales, cold calling, telemarketer, telemarketing, financial advisor, insurance sales, outside sales, outside sales representative, sales executive, salesman, salesperson, sales women, marketing\"},\"end\":{\"local\":\"2019-05-23T13:30:00\",\"timezone\":\"America/Los_Angeles\",\"utc\":\"2019-05-23T20:30:00Z\"},\"format_id\":\"5\",\"hide_end_date\":false,\"hide_start_date\":false,\"id\":\"18720058198\",\"is_free\":true,\"is_locked\":false,\"is_reserved_seating\":false,\"is_series\":false,\"is_series_parent\":false,\"listed\":true,\"locale\":\"en_US\",\"logo\":{\"aspect_ratio\":\"2\",\"crop_mask\":{\"height\":651,\"top_left\":{\"x\":23,\"y\":0},\"width\":1302},\"edge_color\":\"#988d9a\",\"edge_color_set\":true,\"id\":\"18593015\",\"original\":{\"url\":\"https://img.evbuc.com/https%3A%2F%2Fcdn.evbuc.com%2Fimages%2F18593015%2F72054276821%2F1%2Foriginal.jpg?auto\\u003dcompress\\u0026s\\u003d527af6d644cd53656575e0ed50cea883\"},\"url\":\"https://img.evbuc.com/https%3A%2F%2Fcdn.evbuc.com%2Fimages%2F18593015%2F72054276821%2F1%2Foriginal.jpg?h\\u003d200\\u0026w\\u003d450\\u0026auto\\u003dcompress\\u0026rect\\u003d23%2C0%2C1302%2C651\\u0026s\\u003d0857726a5f16597632607b20aacb9fcb\"},\"logo_id\":\"18593015\",\"name\":{\"html\":\"San Jose Career Fair and Job Fair May 23, 2019\",\"text\":\"San Jose Career Fair and Job Fair May 23, 2019\"},\"online_event\":false,\"organization_id\":\"72054276821\",\"organizer_id\":\"4629163895\",\"privacy_setting\":\"unlocked\",\"resource_uri\":\"https://www.eventbriteapi.com/v3/events/18720058198/\",\"shareable\":true,\"source\":\"create_2.0\",\"start\":{\"local\":\"2019-05-23T11:30:00\",\"timezone\":\"America/Los_Angeles\",\"utc\":\"2019-05-23T18:30:00Z\"},\"status\":\"live\",\"subcategory_id\":\"1010\",\"tx_time_limit\":600,\"url\":\"https://www.eventbrite.com/e/san-jose-career-fair-and-job-fair-may-23-2019-tickets-18720058198?aff\\u003debapi\",\"venue_id\":\"29720728\",\"version\":\"3.0.0\"}";
        String venueJson = "{\"address\":{\"address_1\":\"85 Campton Place\",\"address_2\":null,\"city\":\"San Francisco\",\"region\":\"CA\",\"postal_code\":\"94108\",\"country\":\"US\",\"latitude\":\"37.7887903\",\"longitude\":\"-122.4065981\",\"localized_address_display\":\"85 Campton Place, San Francisco, CA 94108\",\"localized_area_display\":\"San Francisco, CA\",\"localized_multi_line_address_display\":[\"85 Campton Place\",\"San Francisco, CA 94108\"]},\"resource_uri\":\"https://www.eventbriteapi.com/v3/venues/29277060/\",\"id\":\"29277060\",\"age_restriction\":null,\"capacity\":null,\"name\":\"Love + Propaganda\",\"latitude\":\"37.7887903\",\"longitude\":\"-122.4065981\"}";
        Gson gson = new Gson();
        Event event = gson.fromJson(eventJson, Event.class);
        Venue venue = gson.fromJson(venueJson, Venue.class);

        MockWebServer server = new MockWebServer();
        server.enqueue(new MockResponse().setResponseCode(200).setBody(venueJson));
        server.start();

        EventBriteRequestManager eventBriteRequestManager =
                new EventBriteRequestManager(server.url("/").toString(),
                        "H7F64OEURMM4B6QBKUAY",
                        "events/search/",
                        "venues/");


        String venueId = "29277060";
        Map<String, Event> venueIDToEventMap = new HashMap<>();
        venueIDToEventMap.put(venueId, event);
        eventBriteRequestManager.requestVenueInformationList(venueIDToEventMap).subscribe(o -> {
            eventWithVenueList = o;
        });

        server.shutdown();

        assertNotNull(eventWithVenueList);
        assertEquals(eventWithVenueList.size(), 1);
        assertEquals(eventWithVenueList.get(0).getEvent(), event);
        assertEquals(eventWithVenueList.get(0).getVenue(), venue);
    }
}
