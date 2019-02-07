import * as AppFormer from "appformer-js";
import * as HomeApi from "kie-wb-common-home-api";
import { StunnerShowcaseHomeScreenProvider } from "./StunnerShowcaseHomeScreenProvider";

AppFormer.register(new HomeApi.HomeScreenAppFormerComponent(new StunnerShowcaseHomeScreenProvider()));
